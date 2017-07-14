package be.gestatech.dashboard.infra.audit.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by amurifa on 14/07/2017.
 *
 * If annotated field gets set, the entity gets audited as 'DELETE'
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditDeleteMarker {

	public static final String DELETE_MARKER = "DELETE_MARKER";

}
