package be.gestatech.dashboard.core.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * BaseEntity object interface
 * Created by amurifa on 30/06/2017.
 */
@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private PK id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@ManyToOne
	@JoinColumn(name = "CREATOR")
	private Users createdBy;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DELETED")
	private boolean deleted;

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Users getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Users createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
