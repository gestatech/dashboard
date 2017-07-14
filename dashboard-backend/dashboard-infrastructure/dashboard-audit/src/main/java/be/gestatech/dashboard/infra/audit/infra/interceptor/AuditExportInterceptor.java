package be.gestatech.dashboard.infra.audit.infra.interceptor;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Created by amurifa on 14/07/2017.
 *
 * Mark the reading of an entity as an EXPORT audit event, annotate the method with <code>@AuditExport</code>
 */
@SuppressWarnings("serial")
@AuditExport
@Interceptor
public class AuditExportInterceptor implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(AuditExportInterceptor.class.getName());

	/**
	 * This flag is set <code>true</code> if this interceptor is used at the start of the transaction. A CREATE audit event is now marked as an IMPORT.
	 */
	public static boolean isExport = false;

	public AuditExportInterceptor() {}

	@AroundInvoke
	public Object toggleActionExport(InvocationContext ctx) throws Exception {

		isExport = true;
		LOGGER.info("Loading entities is now marked as 'EXPORT'");

		try {

			// do whatever you must
			return ctx.proceed();

		} catch (Exception e) {
			LOGGER.severe(() -> String.format("Export failed: {%s}", e.getMessage()));
			return null;
		} finally {
			isExport = false;
			LOGGER.info("Loading entities is back to normal");
		}
	}

}
