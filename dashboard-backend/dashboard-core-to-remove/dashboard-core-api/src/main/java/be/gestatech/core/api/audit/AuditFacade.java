/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.core.api.audit;

import java.io.Serializable;
import java.util.List;

import be.gestatech.core.api.audit.model.AuditEntity;
import be.gestatech.core.api.audit.model.AuditState;

/**
 *
 * @author gestatech
 */
public interface AuditFacade extends Serializable {

    void audit(AuditState state, Object auditObject);

    List<AuditEntity> search(List<String> objectNames, int first, int count);
}
