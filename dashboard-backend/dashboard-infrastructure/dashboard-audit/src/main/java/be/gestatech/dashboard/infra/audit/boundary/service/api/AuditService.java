package be.gestatech.dashboard.infra.audit.boundary.service.api;

import java.util.List;

import be.gestatech.dashboard.infra.audit.entity.AuditEvent;
import be.gestatech.dashboard.infra.audit.entity.AuditInitialValues;
import be.gestatech.dashboard.infra.audit.infra.constant.AuditAction;

/**
 * Created by amurifa on 13/07/2017.
 */
public interface AuditService {

	List<AuditEvent> getEvents(String entity);

	List<AuditEvent> getEvents(String entity, Long entityId);

	void createAuditEvent(AuditInitialValues entity, AuditAction action);
}