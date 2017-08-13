package be.gestatech.dashboard.core.jpa.entity.method;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User's methodType
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = MethodTypes.TABLE_NAME)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "MethodTypes.findAll", query = "SELECT m FROM MethodTypes m"),
	@NamedQuery(name = "MethodTypes.findByMethodTypeId", query = "SELECT m FROM MethodTypes m WHERE m.methodTypeId = :methodTypeId"),
	@NamedQuery(name = "MethodTypes.findByShortName", query = "SELECT m FROM MethodTypes m WHERE m.shortName = :shortName"),
	@NamedQuery(name = "MethodTypes.findByFullName", query = "SELECT m FROM MethodTypes m WHERE m.fullName = :fullName"),
	@NamedQuery(name = "MethodTypes.findByDateCreated", query = "SELECT m FROM MethodTypes m WHERE m.dateCreated = :dateCreated"),
	@NamedQuery(name = "MethodTypes.findByDeleted", query = "SELECT m FROM MethodTypes m WHERE m.deleted = :deleted")
})
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "METHOD_TYPE_ID"))
public class MethodTypes extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 3383111202158692806L;

	public static final String TABLE_NAME = "METHOD_TYPE";

	@Column(name = "METHOD_TYPE_ID")
	private Long methodTypeId;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 25)
	@Column(name = "SHORT_NAME")
	private String shortName;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "FULL_NAME")
	private String fullName;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DELETED")
	private boolean deleted;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "METHOD_TYPE")
	private Collection<Methods> methodsCollection;

	public MethodTypes() {
	}

	public Long getMethodTypeId() {
		return methodTypeId;
	}

	public void setMethodTypeId(Long methodTypeId) {
		this.methodTypeId = methodTypeId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Users getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	public Collection<Methods> getMethodsCollection() {
		return methodsCollection;
	}

	public void setMethodsCollection(Collection<Methods> methodsCollection) {
		this.methodsCollection = methodsCollection;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MethodTypes)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		MethodTypes that = (MethodTypes) o;
		return isDeleted() == that.isDeleted() && Objects.equals(getMethodTypeId(), that.getMethodTypeId()) && Objects.equals(getShortName(), that.getShortName()) && Objects.equals(getFullName(), that.getFullName()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated()) && Objects.equals(getMethodsCollection(), that.getMethodsCollection());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getMethodTypeId(), getShortName(), getFullName(), getDateCreated(), isDeleted(), getUserCreated(), getMethodsCollection());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MethodTypes{");
		sb.append("methodTypeId=").append(methodTypeId);
		sb.append(", shortName='").append(shortName).append('\'');
		sb.append(", fullName='").append(fullName).append('\'');
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append(", methodsCollection=").append(methodsCollection);
		sb.append('}');
		return sb.toString();
	}
}
