package be.gestatech.dashboard.infra.audit.infra.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Created by amurifa on 14/07/2017.
 *
 * Mark the reading of an entity as an EXPORT audit event for the current transaction started by the annotated method <br>
 * It needs to get declare in beans.xml
 *
 * <pre>
 * &lt;interceptors&gt;
 *     &lt;class&gt;be.gestatech.dashboard.infra.audit.infra.interceptor.AuditExportInterceptor&lt;/class&gt;
 * &lt;/interceptors&gt;
 * </pre>
 *
 */
@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE})
public @interface AuditExport {
}