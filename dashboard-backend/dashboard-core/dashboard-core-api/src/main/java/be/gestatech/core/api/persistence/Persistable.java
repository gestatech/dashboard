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

    boolean isNew();

    Date getCreateDate();

    void setCreateDate(Date createDate);

    void setUpdateDate(Date updateDate);

    Date getUpdateDate();
}
