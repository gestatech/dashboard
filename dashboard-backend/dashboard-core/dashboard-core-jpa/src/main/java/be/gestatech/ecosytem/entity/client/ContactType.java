package be.gestatech.ecosytem.entity.client;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.core.api.persistence.Persistable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by amuri on 6/25/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = ContactType.CONTACT_TYPE_ENTITY + ".findAll", query = "select ct from ContactType ct order by ct.name"),})
@javax.persistence.EntityListeners(DateUpdateListener.class)
@javax.persistence.Table(name = "CONTACT_TYPE")
public class ContactType implements Persistable, Serializable, Comparable<ContactType> {

    private static final long serialVersionUID = -4668753541074249650L;

    public static final String CONTACT_TYPE_ENTITY = "ContactType";

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }

    @Override
    public Date getCreated() {
        return null;
    }

    @Override
    public void setCreated(Date created) {

    }

    @Override
    public void setUpdated(Date updated) {

    }

    @Override
    public Date getUpdated() {
        return null;
    }

    @Override
    public int compareTo(ContactType o) {
        return 0;
    }
}
