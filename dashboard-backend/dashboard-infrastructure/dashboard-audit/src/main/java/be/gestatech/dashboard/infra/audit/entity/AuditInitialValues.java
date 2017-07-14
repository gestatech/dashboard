package be.gestatech.dashboard.infra.audit.entity;

import java.util.Map;

/**
 * Created by amurifa on 13/07/2017.
 */
public interface AuditInitialValues {

	public Long getEntiyId();

	public Map<String, Object> getInitialValues();

	public void setInitialValues(Map<String, Object> initialValues);

}
