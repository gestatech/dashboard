package be.gestatech.dashboard.infra.audit.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import be.gestatech.dashboard.infra.audit.infra.constant.AuditAction;
import be.gestatech.dashboard.infra.audit.infra.config.AuditConfig;

/**
 * Created by amurifa on 12/07/2017.
 */
@Entity
@Table(name = AuditEvent.TABLE_NAME)
@NamedQueries({
	@NamedQuery(name = AuditEvent.TABLE_NAME + ".getAllEvents", query = "select av from AuditEvent av where av.entity = :entity order by av.createdAt desc"),
	@NamedQuery(name = AuditEvent.TABLE_NAME + ".getAllEventsForId", query = "select av from AuditEvent av where av.entity = :entity and av.entityId = :entityId order by av.createdAt desc"),
	@NamedQuery(name = AuditEvent.TABLE_NAME + ".getLatestEvent", query = "select av from AuditEvent av where av.entity = :entity and av.entityId = :entityId and av.createdAt >= :fromDate order by av.createdAt desc")
})
public class AuditEvent implements Serializable{

	private static final long serialVersionUID = -2444603287821397672L;

	public final static String TABLE_NAME = "AUDIT_EVENT";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUDIT_EVENT_ID")
	private Long id;

	@Column(name = "CREATED_AT", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "ENTITY", nullable = false)
	private String entity;

	@Column(name = "ENTITY_ID", nullable = false)
	private Long entityId;

	@Column(name = "ACTION", nullable = false)
	@Enumerated(EnumType.STRING)
	private AuditAction action;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@OneToMany(mappedBy = "EVENT", fetch = FetchType.LAZY)
	private List<AuditChange> changes;

	@PrePersist
	public void prePesist() {
		if (Objects.isNull(createdAt)) {
			createdAt = LocalDateTime.now(ZoneId.of(AuditConfig.LOCAL_DATE_TIME_ZONE));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public AuditAction getAction() {
		return action;
	}

	public void setAction(AuditAction action) {
		this.action = action;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<AuditChange> getChanges() {
		return changes;
	}

	public void setChanges(List<AuditChange> changes) {
		this.changes = changes;
	}

	@SuppressWarnings("unchecked")
	public static List<AuditEvent> getAllEvents(EntityManager entityManager, String entity) {
		Query query = entityManager.createNamedQuery(AuditEvent.TABLE_NAME + ".getAllEvents");
		query = query.setParameter("entity", entity);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<AuditEvent> getAllEventsForId(EntityManager entityManager, String entity, Long entityId) {
		Query query = entityManager.createNamedQuery(AuditEvent.TABLE_NAME + ".getAllEventsForId");
		query = query.setParameter("entity", entity);
		query = query.setParameter("entityId", entityId);
		return query.getResultList();
	}


	public static AuditEvent getLatestEvent(EntityManager entityManager, String entity, Long entityId, LocalDateTime fromDate) {
		AuditEvent auditEvent = null;
		Query query = entityManager.createNamedQuery(AuditEvent.TABLE_NAME + ".getLatestEvent");
		query = query.setParameter("entity", entity);
		query = query.setParameter("entityId", entityId);
		query = query.setParameter("fromDate", fromDate);
		query = query.setMaxResults(1);
		@SuppressWarnings("rawtypes")
		List results = query.getResultList();
		if (!results.isEmpty()) {
			auditEvent =(AuditEvent) results.get(0) ;
		}
		return auditEvent;
	}


}
