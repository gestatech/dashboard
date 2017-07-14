package be.gestatech.dashboard.infra.audit.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

/**
 * Created by amurifa on 14/07/2017.
 *
 * Multiple updates whithin a timeframe (default 60 seconds) gets grouped to a single audit event. Multiple changes on the same field will result in overwrite
 * the in favour for the latest (default overwriteChanges). This is suitable for auto-save behaviour.
 */
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditGroupEvents {

	@Nonbinding
	long timeframe() default 60;

	@Nonbinding
	boolean overwriteChanges() default true;

}
