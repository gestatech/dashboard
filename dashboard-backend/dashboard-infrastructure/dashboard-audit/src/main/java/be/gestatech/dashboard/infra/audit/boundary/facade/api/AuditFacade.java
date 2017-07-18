package be.gestatech.dashboard.infra.audit.boundary.facade.api;

import java.util.List;

import javax.ejb.Local;

import be.gestatech.dashboard.infra.audit.entity.AuditEvent;
import be.gestatech.dashboard.infra.audit.entity.AuditInitialValues;
import be.gestatech.dashboard.infra.audit.infra.constant.AuditAction;

/**
 * Created by amurifa on 18/07/2017.
 */
public interface AuditFacade {

	List<AuditEvent> getEvents(String entity);

	List<AuditEvent> getEvents(String entity, Long entityId);

	void createAuditEvent(AuditInitialValues entity, AuditAction action);
}
