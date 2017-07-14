package be.gestatech.dashboard.infra.audit.entity.api;

import javax.persistence.EntityManager;

/**
 * Created by amurifa on 13/07/2017.
 */
public interface AuditManagedReadable {

	/**
	 * @param entityManager EntityManager
	 * @return String representation of current object for the usage in auditing
	 */
	public String toAuditableString(EntityManager entityManager);

}