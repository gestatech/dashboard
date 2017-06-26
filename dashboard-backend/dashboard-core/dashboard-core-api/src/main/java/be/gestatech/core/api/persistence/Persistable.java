/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.core.api.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gestatech
 */
public interface Persistable extends Serializable {

    Long getId();

    void setId(Long id);

    Date getCreated();

    void setCreated(Date created);

    void setUpdated(Date updated);

    Date getUpdated();
}
