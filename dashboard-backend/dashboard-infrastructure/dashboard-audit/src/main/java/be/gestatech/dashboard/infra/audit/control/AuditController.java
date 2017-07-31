package be.gestatech.dashboard.infra.audit.control;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NoResultException;

import be.gestatech.dashboard.infra.audit.entity.AuditChange;
import be.gestatech.dashboard.infra.audit.entity.AuditEvent;
import be.gestatech.dashboard.infra.audit.entity.AuditInitialValues;
import be.gestatech.dashboard.infra.audit.entity.api.AuditManagedReadable;
import be.gestatech.dashboard.infra.audit.entity.api.AuditReadable;
import be.gestatech.dashboard.infra.audit.infra.annotation.AuditDeleteMarker;
import be.gestatech.dashboard.infra.audit.infra.annotation.AuditEntityManager;
import be.gestatech.dashboard.infra.audit.infra.annotation.AuditGroupEvents;
import be.gestatech.dashboard.infra.audit.infra.annotation.AuditGroupEventsBreak;
import be.gestatech.dashboard.infra.audit.infra.annotation.AuditRelatedEntity;
import be.gestatech.dashboard.infra.audit.infra.config.AuditConfig;
import be.gestatech.dashboard.infra.audit.infra.constant.AuditAction;
import be.gestatech.dashboard.infra.audit.infra.dto.impl.ChangeDTO;
import be.gestatech.dashboard.infra.audit.infra.dto.impl.UserDTO;
import be.gestatech.dashboard.infra.audit.infra.util.AuditUtil;

/**
 * Created by amurifa on 13/07/2017.
 */
@Stateless
public class AuditController {

	private static Logger LOGGER = Logger.getLogger(AuditController.class.getName());

	@Inject
	@AuditEntityManager
	EntityManager entityManager;

	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void createAuditEventAsynchronous(AuditInitialValues entity, AuditAction auditAction, UserDTO auditUser, LocalDateTime createdAt) {
		createAuditEvent(entity, auditAction, auditUser, createdAt);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void createAuditEvent(AuditInitialValues entity, AuditAction auditAction, UserDTO auditUser, LocalDateTime createdAt) {

		if (auditUser.getUserName() == null || auditUser.getUserName().isEmpty()) {
			auditUser.setUserName(AuditConfig.DEFAULT_USER);
		}
		String entityName = AuditUtil.getEntityName(entity);
		boolean grouping = false;

		// get dem changes
		List<AuditChange> auditChanges;
		AuditAction action = auditAction;
		switch (action) {
			case CREATE:
			case IMPORT:
				auditChanges = getAuditChanges(entity, true);
				break;
			case UPDATE:
				auditChanges = getAuditChanges(entity, false);
				if (auditChanges.stream().map(AuditChange::getField).filter(AuditDeleteMarker.DELETE_MARKER::equals).findFirst().isPresent()) {
					action = AuditAction.DELETE; // If delete marker is triggered, the update shall be audited as a deletion
				}
				grouping = AuditUtil.groupEvents(entity, action);
				break;
			default:
				auditChanges = new ArrayList<>();
				break;
		}

		if (grouping) {

			AuditGroupEvents groupSettings = entity.getClass().getAnnotation(AuditGroupEvents.class);
			// try grouping with older event to join changes
			grouping = groupChangesWithLatestEvent(entity.getClass(), entity.getEntiyId(), auditChanges, groupSettings.timeframe(),
					groupSettings.overwriteChanges(), auditUser);
		}
		if (!grouping) {

			// audit it the normal way
			AuditEvent auditEvent = createAuditEvent(entityName, entity.getEntiyId(), action, auditChanges, auditUser, createdAt);

			if (!action.equals(AuditAction.EXPORT) && !action.equals(AuditAction.IMPORT)) {
				// updated parent audit events
				Map<Class<?>, Long> parentReferences = AuditUtil.getParentReferences(entity);
				if (parentReferences.size() > 0) {

					String subEventName = toAuditableString(entity);

					for (Map.Entry<Class<?>, Long> parentReference : parentReferences.entrySet()) {

						Class<?> entityClass = parentReference.getKey();
						Long entityId = parentReference.getValue();

						AuditChange parentReferenceChange = new AuditChange();
						parentReferenceChange.setField(entityName);
						parentReferenceChange.setSubEvent(auditEvent);
						parentReferenceChange.setSubEventName(subEventName);

						if (!groupChangesWithLatestEvent(entityClass, entityId, Arrays.asList(parentReferenceChange), 10, false, auditUser)) {

							createAuditEvent(AuditUtil.getEntityName(entityClass), entityId, AuditAction.UPDATE, Arrays.asList(parentReferenceChange),
									auditUser, createdAt);
						}
					}
				}
			}
			LOGGER.info(() -> String.format("Audit event written: {%s}", auditEvent.getId()));
		}
	}

	private List<AuditChange> getAuditChanges(AuditInitialValues entity, boolean initial) {

		List<AuditChange> auditChanges = new ArrayList<>();

		Map<String, Object> newValues = AuditUtil.getValues(entity);
		Map<String, Object> oldValues = null;
		if (initial) {
			try {
				oldValues = AuditUtil.getValues(entity.getClass().getConstructor().newInstance());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				LOGGER.severe(() -> String.format("Could not instantiate {%s} {%s}", entity.getClass().getName(), e));
			}
		} else {
			oldValues = entity.getInitialValues();
		}

		Map<String, Field> nameFieldMapping = AuditUtil.getNameFieldMapping(entity);

		for (ChangeDTO changeDTO : getChanges(oldValues, newValues)) {

			AuditChange auditChange = new AuditChange();
			auditChange.setField(changeDTO.getField());

			Field field = nameFieldMapping.get(changeDTO.getField());
			if (field != null && field.isAnnotationPresent(AuditRelatedEntity.class)) {
				auditChange.setOldValue(getRelatedEntityValue(field.getAnnotation(AuditRelatedEntity.class).value(), changeDTO.getOldValue()));
				auditChange.setNewValue(getRelatedEntityValue(field.getAnnotation(AuditRelatedEntity.class).value(), changeDTO.getNewValue()));
			} else {
				auditChange.setOldValue(changeDTO.getOldValueString());
				auditChange.setNewValue(changeDTO.getNewValueString());
			}
			auditChanges.add(auditChange);
		}
		return auditChanges;
	}

	private AuditEvent createAuditEvent(String entity, Long entiyId, AuditAction action, List<AuditChange> auditChanges, UserDTO auditUser,
	                                    LocalDateTime createdAt) {

		AuditEvent auditEvent = new AuditEvent();
		auditEvent.setEntity(entity);
		auditEvent.setEntityId(entiyId);
		auditEvent.setAction(action);
		auditEvent.setUserName(auditUser.getUserName());
		auditEvent.setUserId(auditUser.getUserId());
		auditEvent.setCreatedAt(createdAt);

		entityManager.persist(auditEvent);

		auditChanges.forEach(auditChange -> {
			auditChange.setEvent(auditEvent);
			entityManager.persist(auditChange);
		});

		return auditEvent;
	}

	private boolean groupChangesWithLatestEvent(Class<?> entityClass, Long entityId, List<AuditChange> auditChanges, long timeframe, boolean overwriteChanges,
	                                            UserDTO auditUser) {

		LocalDateTime groupDateLimit = LocalDateTime.now(ZoneId.of(AuditConfig.LOCAL_DATE_TIME_ZONE)).minusSeconds(timeframe);

		AuditEvent latestEvent = AuditEvent.getLatestEvent(entityManager, AuditUtil.getEntityName(entityClass), entityId, groupDateLimit);

		if (latestEvent != null && (latestEvent.getAction().equals(AuditAction.CREATE) || latestEvent.getAction().equals(AuditAction.UPDATE))
				&& (Objects.equals(latestEvent.getUserId(), auditUser.getUserId()) || latestEvent.getUserName().equals(auditUser.getUserName()))) {

			// check if field want's its own event and breaks this grouping with no survivors
			List<String> groupBreakignFields = new ArrayList<>();
			for (Field field : AuditUtil.getFields(entityClass)) {
				if (field.isAnnotationPresent(AuditGroupEventsBreak.class)) {
					groupBreakignFields.add(AuditUtil.getFieldName(field));
				}
			}
			for (AuditChange auditChange : auditChanges) {
				if (groupBreakignFields.contains(auditChange.getField())) {
					return false;
				}
			}

			if (overwriteChanges) {

				// set latest values
				for (AuditChange newChange : auditChanges) {

					boolean newField = true;

					for (AuditChange oldChange : latestEvent.getChanges()) {
						if (oldChange.getField().equals(newChange.getField())) {

							oldChange.setNewValue(newChange.getNewValue());
							newField = false;
							break;
						}
					}
					if (newField) {
						newChange.setEvent(latestEvent);
						entityManager.persist(newChange);
					}
				}
			} else {
				// just add new changes
				auditChanges.forEach(auditChange -> {
					auditChange.setEvent(latestEvent);
					entityManager.persist(auditChange);
				});
			}
			// reset time on event
			latestEvent.setCreatedAt(LocalDateTime.now(ZoneId.of(AuditConfig.LOCAL_DATE_TIME_ZONE)));
			LOGGER.info(() -> String.format("Audit changes grouped with event: {%s}", latestEvent.getId()));
			return true;
		}
		return false;
	}

	private String toAuditableString(Object entity) {
		if (entity instanceof AuditManagedReadable) {
			return ((AuditManagedReadable) entity).toAuditableString(entityManager);
		}
		if (entity instanceof AuditReadable) {
			return ((AuditReadable) entity).toAuditableString();
		}
		return entity.toString();
	}

	private String getRelatedEntityValue(Class<?> relatedEntityClass, Object value) {

		if (value == null) {
			return null;
		}
		for (Field field : AuditUtil.getFields(relatedEntityClass)) {

			if (field.isAnnotationPresent(Id.class)) {
				if (field.getType() == value.getClass()) {
					try {
						Object relatedEntity = entityManager.find(relatedEntityClass, value);
						return toAuditableString(relatedEntity);
					} catch (NoResultException e) {
						LOGGER.warning(() -> String.format("Related Entity not found: {%s} ID {%s}", relatedEntityClass, value));
					}
				}
				break;
			}
		}
		return value.toString();
	}

	private Set<ChangeDTO> getChanges(Map<String, Object> oldValues, Map<String, Object> newValues) {
		Set<ChangeDTO> changeses = new HashSet<>();
		for (Map.Entry<String, Object> entry : newValues.entrySet()) {
			Object oldForNew = oldValues.get(entry.getKey());
			if (oldForNew == null) {
				if (entry.getValue() != null) {
					changeses.add(new ChangeDTO(null, entry));
				}
			} else {
				if (!oldForNew.equals(entry.getValue())) {
					changeses.add(new ChangeDTO(oldForNew, entry));
				}
			}
		}
		return changeses;
	}
}
