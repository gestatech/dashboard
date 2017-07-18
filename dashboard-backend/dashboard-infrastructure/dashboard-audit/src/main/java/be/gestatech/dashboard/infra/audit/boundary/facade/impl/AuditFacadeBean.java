package be.gestatech.dashboard.infra.audit.boundary.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;

import be.gestatech.dashboard.infra.audit.boundary.facade.api.AuditFacade;
import be.gestatech.dashboard.infra.audit.boundary.service.api.AuditService;
import be.gestatech.dashboard.infra.audit.entity.AuditEvent;
import be.gestatech.dashboard.infra.audit.entity.AuditInitialValues;
import be.gestatech.dashboard.infra.audit.infra.constant.AuditAction;

/**
 * Created by amurifa on 18/07/2017.
 */
@Local(value = AuditFacade.class)
public class AuditFacadeBean implements AuditFacade {

	@EJB
	AuditService auditService;

	@Override
	public List<AuditEvent> getEvents(String entity) {
		return auditService.getEvents(entity);
	}

	@Override
	public List<AuditEvent> getEvents(String entity, Long entityId) {
		return auditService.getEvents(entity, entityId);
	}

	@Override
	public void createAuditEvent(AuditInitialValues entity, AuditAction action) {
		auditService.createAuditEvent(entity, action);
	}
}
