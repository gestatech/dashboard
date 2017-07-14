package be.gestatech.dashboard.infra.audit.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

/**
 * Created by amurifa on 14/07/2017.
 *
 * Date/Time pattern for the value representation in audit entry
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditDateTimePattern {

	@Nonbinding
	String value() default "dd.MM.yyyy";

}
