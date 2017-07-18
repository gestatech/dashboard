package be.gestatech.dashboard.infra.audit.infra.interceptor;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Created by amurifa on 18/07/2017.
 *
 * To mark a CREATE audit event as an IMPORT audit event, annotate the method with <code>@AuditImport</code>
 */
@SuppressWarnings("serial")
@AuditImport
@Interceptor
public class AuditImportInterceptor implements Serializable {

	private static final long serialVersionUID = -4342495374497100542L;

	private static Logger LOGGER = Logger.getLogger(AuditImportInterceptor.class.getName());

	/**
	 * This flag is set <code>true</code> if this interceptor is used at the start of the transaction. A CREATE audit event is now marked as an IMPORT.
	 */
	public static boolean isImport = false;

	public AuditImportInterceptor() {}

	@AroundInvoke
	public Object toggleActionImport(InvocationContext ctx) throws Exception {

		isImport = true;
		LOGGER.info("Audit event 'CREATE' is now marked as 'IMPORT'");

		try {

			// do a persist and get audited as an import
			return ctx.proceed();

		} catch (Exception e) {
			LOGGER.severe(() -> String.format("Import failed: {%s}", e.getMessage()));
			return null;
		} finally {

			isImport = false;
			LOGGER.info("Audit event 'CREATE' is back to normal");
		}
	}

}
