/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.core.api.audit.model;

import java.util.List;

import be.gestatech.core.api.bookmark.Bookmarkable;
import be.gestatech.core.api.persistence.Auditable;

/**
 *
 * @author gestatech
 */

public interface AuditEntity extends Auditable, Bookmarkable {

    <T extends AuditEntityItem> List<T> getAuditEntityItems();

    AuditState getAuditState();

    void setAuditState(AuditState auditState);

    String getAuditableName();

    void setAuditableName(String auditableName);
}
