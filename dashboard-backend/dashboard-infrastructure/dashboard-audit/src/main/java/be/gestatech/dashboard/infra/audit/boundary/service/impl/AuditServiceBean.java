package be.gestatech.dashboard.infra.audit.boundary.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import be.gestatech.dashboard.infra.audit.boundary.service.api.AuditService;
import be.gestatech.dashboard.infra.audit.control.AuditController;
import be.gestatech.dashboard.infra.audit.entity.AuditEvent;
import be.gestatech.dashboard.infra.audit.entity.AuditInitialValues;
import be.gestatech.dashboard.infra.audit.infra.annotation.AuditEntityManager;
import be.gestatech.dashboard.infra.audit.infra.config.AuditConfig;
import be.gestatech.dashboard.infra.audit.infra.constant.AuditAction;
import be.gestatech.dashboard.infra.audit.infra.dto.api.AuditableUser;
import be.gestatech.dashboard.infra.audit.infra.dto.impl.UserDTO;
import be.gestatech.dashboard.infra.audit.infra.util.AuditUtil;

/**
 * Created by amurifa on 13/07/2017.
 */
public class AuditServiceBean implements AuditService {

	@Inject
	@AuditEntityManager
	EntityManager entityManager;

	@Inject
	AuditableUser auditUser;

	@EJB
	AuditController auditController;

	@Override
	public List<AuditEvent> getEvents(String entity) {
		return AuditEvent.getAllEvents(entityManager, entity);
	}

	@Override
	public List<AuditEvent> getEvents(String entity, Long entityId) {
		return AuditEvent.getAllEventsForId(entityManager, entity, entityId);
	}

	@Override
	public void createAuditEvent(AuditInitialValues entity, AuditAction action) {

		LocalDateTime createdAt = LocalDateTime.now(ZoneId.of(AuditConfig.LOCAL_DATE_TIME_ZONE));

		if (AuditUtil.groupEvents(entity, action)) {
			auditController.createAuditEvent(entity, action, getUser(), createdAt);
		} else {
			auditController.createAuditEventAsynchronous(entity, action, getUser(), createdAt);
		}
	}

	private UserDTO getUser() {
		return new UserDTO(auditUser);
	}
}
