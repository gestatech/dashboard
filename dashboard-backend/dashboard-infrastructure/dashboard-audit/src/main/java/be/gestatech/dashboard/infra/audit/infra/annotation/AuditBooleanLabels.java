package be.gestatech.dashboard.infra.audit.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

/**
 * Created by amurifa on 14/07/2017.
 *
 * Labels for the boolean value representation in audit entry
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditBooleanLabels {

	@Nonbinding
	String trueLabel() default "TRUE";

	@Nonbinding
	String falseLabel() default "FALSE";

}