package be.gestatech.dashboard.infra.audit.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

/**
 * Created by amurifa on 14/07/2017.
 *
 * Overwrites the name of a entity or field for the use in the audit record
 */
@Target({ ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditName {

	@Nonbinding
	String value();
}
