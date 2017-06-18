/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.core.api.audit;

import be.gestatech.core.api.audit.annotation.Audited;
import be.gestatech.core.api.interceptor.AbstractInterceptor;
import be.gestatech.core.api.interceptor.Signature;
import java.util.Objects;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

/**
 *
 * @author gestatech
 */
@Audited
@Interceptor
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AuditInterceptor extends AbstractInterceptor<Audited> {

    private static final long serialVersionUID = 1116420616722293860L;
    
    private static final Logger LOGGER = Logger.getLogger(AuditInterceptor.class.getName());

    @Inject
    private AuditFacade auditFacade;

    @Override
    protected void beforeProceed(final Signature signature, final Audited audited) {
        Object[] params = signature.getParameters();
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(
                String.format("Audit interceptor got class[%s], method [%s] with state [%s], parameter count [%d], first parameter [%s]",
                signature.getDeclaringTypeName(), signature.getMethodName(), audited.state(),
                params.length, (params.length > 0) ? params[0] : "")
            );
        }
        if (Objects.nonNull(params) && params.length > 0) {
            auditFacade.audit(audited.state(), params[0]);
        }
    }

    @Override
    protected Class<Audited> getAnnotationClass() {
        return Audited.class;
    }
}
