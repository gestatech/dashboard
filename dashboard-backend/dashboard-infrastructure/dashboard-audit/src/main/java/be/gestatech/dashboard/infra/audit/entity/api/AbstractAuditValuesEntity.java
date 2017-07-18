package be.gestatech.dashboard.infra.audit.entity.api;

import java.util.Map;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import be.gestatech.dashboard.infra.audit.entity.AuditInitialValues;
import be.gestatech.dashboard.infra.audit.entity.listener.AuditEntityListener;

/**
 * Created by amurifa on 13/07/2017.
 */
@MappedSuperclass
@EntityListeners(AuditEntityListener.class)
public abstract class AbstractAuditValuesEntity implements AuditInitialValues {

	@Transient
	private transient Map<String, Object> initialValues;

	@Override
	public Map<String, Object> getInitialValues() {
		return initialValues;
	}

	@Override
	public void setInitialValues(Map<String, Object> initialValues) {
		this.initialValues = initialValues;
	}

}
