package be.gestatech.dashboard.infra.audit.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by amurifa on 14/07/2017.
 *
 * Marks the object witch related to the parent entity (one) in an @ManyToOne relation
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditCollectionParent {

}
