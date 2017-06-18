package be.gestatech.ecosytem.entity.client;

import be.gestatech.core.api.persistence.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by amuri on 6/18/2017.
 */
public class ClientNote implements Persistable, Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id", nullable=false, length=19)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Created", nullable=false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Updated", nullable=false)
    private Date updated;

    @Column(name="Detail", nullable=false)
    private String detail;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }

    @Override
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
