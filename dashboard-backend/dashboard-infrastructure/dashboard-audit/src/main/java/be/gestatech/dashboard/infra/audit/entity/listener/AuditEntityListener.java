package be.gestatech.dashboard.infra.audit.entity.listener;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PreUpdate;

import be.gestatech.dashboard.infra.audit.boundary.service.api.AuditService;
import be.gestatech.dashboard.infra.audit.entity.AuditInitialValues;
import be.gestatech.dashboard.infra.audit.infra.constant.AuditAction;
import be.gestatech.dashboard.infra.audit.infra.interceptor.AuditExportInterceptor;
import be.gestatech.dashboard.infra.audit.infra.util.AuditUtil;

/**
 * Created by amurifa on 13/07/2017.
 */
@Stateless
public class AuditEntityListener {

	private static final Logger LOGGER = Logger.getLogger(AuditEntityListener.class.getName());

	@EJB
	private AuditService auditService;

	@PostLoad
	public void init(AuditInitialValues entity) {
		entity.setInitialValues(AuditUtil.getValues(entity));
		if (AuditExportInterceptor.isExport) {
			LOGGER.warning(() -> String.format("Audit {} Export", entity.getClass().getSimpleName()));
			auditService.createAuditEvent(entity, AuditAction.EXPORT);
		}
	}

	@PostPersist
	public void create(AuditInitialValues entity) {
		if (AuditImportInterceptor.isImport) {
			LOGGER.warning(() -> String.format("Audit {%s} Import", entity.getClass().getSimpleName()));
			auditService.createAuditEvent(entity, AuditAction.IMPORT);
		} else {
			LOGGER.warning(() -> String.format("Audit {} Create", entity.getClass().getSimpleName()));
			auditService.createAuditEvent(entity, AuditAction.CREATE);
		}
		entity.setInitialValues(AuditUtil.getValues(entity));
	}

	@PreUpdate
	public void update(AuditInitialValues entity) {
		LOGGER.warning(() -> String.format("Audit {} Update", entity.getClass().getSimpleName()));
		auditService.createAuditEvent(entity, AuditAction.UPDATE);
	}

	@PostRemove
	public void delete(AuditInitialValues entity) {
		LOGGER.warning(() -> String.format("Audit {} Delete", entity.getClass().getSimpleName()));
		auditService.createAuditEvent(entity, AuditAction.DELETE);
	}

}