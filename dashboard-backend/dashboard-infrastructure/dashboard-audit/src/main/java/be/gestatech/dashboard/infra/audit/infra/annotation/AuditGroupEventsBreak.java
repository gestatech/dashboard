package be.gestatech.dashboard.infra.audit.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by amurifa on 14/07/2017.
 *
 * Breaks the behavior that multiple updates gets grouped to a single audit event. To use with {@link AuditGroupEvents}
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditGroupEventsBreak {
}
