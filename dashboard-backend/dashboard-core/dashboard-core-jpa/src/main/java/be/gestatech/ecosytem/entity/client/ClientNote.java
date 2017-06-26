package be.gestatech.ecosytem.entity.client;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.core.api.persistence.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by amuri on 6/18/2017.
 */
@Entity
@Table(name="CLIENT_NOTE")
@EntityListeners(DateUpdateListener.class)
public class ClientNote implements Persistable, Serializable {

    private static final long serialVersionUID = -1590334282175803511L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id", nullable=false, length=19)
    private Long id;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientNote)) return false;
        ClientNote that = (ClientNote) o;
        return getId() == that.getId() &&
                Objects.equals(getCreated(), that.getCreated()) &&
                Objects.equals(getUpdated(), that.getUpdated()) &&
                Objects.equals(getDetail(), that.getDetail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreated(), getUpdated(), getDetail());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientNote{");
        sb.append("id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", detail='").append(detail).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
