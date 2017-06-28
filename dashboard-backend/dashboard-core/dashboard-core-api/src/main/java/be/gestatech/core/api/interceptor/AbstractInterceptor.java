/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.core.api.interceptor;

import be.gestatech.dashboard.infra.util.ClassUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author gestatech
 */
public abstract class AbstractInterceptor<T extends Annotation> implements Serializable {

    private static final long serialVersionUID = 1354190869327479091L;

    private static final Logger LOGGER = Logger.getLogger(AbstractInterceptor.class.getName());

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
        Method method = ic.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            method = ClassUtil.getMethodIfAvailable(ic.getTarget().getClass(), method.getName(), method.getParameterTypes());
        }
        if (Objects.isNull(method)) {
            throw new RuntimeException("Method not found!");
        }
        Signature signature = new Signature()
                .setMethodName(method.getName())
                .setDeclaringType(method.getDeclaringClass())
                .setDeclaringTypeName(method.getDeclaringClass().getName())
                .setParameters(ic.getParameters());
        try {
            beforeProceed(signature, method.getAnnotation(getAnnotationClass()));
        } catch (Exception e) {
            LOGGER.severe(String.format("Could not perform action [%s].", e));
        }
        return ic.proceed();
    }

    protected abstract Class<T> getAnnotationClass();

    protected abstract void beforeProceed(Signature signature, T annotation);
}
