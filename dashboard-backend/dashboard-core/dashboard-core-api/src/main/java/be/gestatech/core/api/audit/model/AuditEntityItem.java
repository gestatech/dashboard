/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.core.api.audit.model;

import be.gestatech.core.api.persistence.Persistable;

/**
 *
 * @author gestatech
 */
public interface AuditEntityItem extends Persistable {
     
    AuditEntity getAuditEntity();

    void setAuditEntity(AuditEntity auditEntity);

    String getName();

    void setName(String name);

    String getValue();

    void setValue(String value);

    boolean isKey();

    void setKey(boolean key);
    
}
