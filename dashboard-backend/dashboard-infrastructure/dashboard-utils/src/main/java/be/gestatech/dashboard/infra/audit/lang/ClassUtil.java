/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.dashboard.infra.audit.lang;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 *
 * @author gestatech
 */
public class ClassUtil {

    public static Method getMethodIfAvailable(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        if (Objects.isNull(clazz)) {
            return null;
        }
        try {
            return clazz.getDeclaredMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            // try superclass
            return getMethodIfAvailable(clazz.getSuperclass(), methodName, paramTypes);
        }
    }
}
