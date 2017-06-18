/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.core.api.interceptor;

import java.io.Serializable;

/**
 *
 * @author gestatech
 */
public class Signature implements Serializable {

    private static final long serialVersionUID = 5571882580323388141L;
    
    private String methodName;

    @SuppressWarnings("rawtypes")
    private Class declaringType;

    private String declaringTypeName;

    private Object[] parameters;

    
    public Object[] getParameters() {
        return parameters;
    }

    public Signature setParameters(Object[] parameters) {
        this.parameters = parameters;
        return this;
    }

    @SuppressWarnings("rawtypes")
    public Class getDeclaringType() {
        return declaringType;
    }

    @SuppressWarnings("rawtypes")
    public Signature setDeclaringType(Class declaringType) {
        this.declaringType = declaringType;
        return this;
    }

    public String getDeclaringTypeName() {
        return declaringTypeName;
    }

    public Signature setDeclaringTypeName(String declaringTypeName) {
        this.declaringTypeName = declaringTypeName;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public Signature setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    } 
}
