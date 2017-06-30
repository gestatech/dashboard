package be.gestatech.ecosytem.entity.method;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.ecosytem.entity.Identifiable;
import be.gestatech.ecosytem.entity.user.Users;

/**
 * Entity class Describes User's methodType
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "METHOD_TYPE")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "MethodTypes.findAll", query = "SELECT m FROM MethodTypes m"),
	@NamedQuery(name = "MethodTypes.findByMethodTypeId", query = "SELECT m FROM MethodTypes m WHERE m.methodTypeId = :methodTypeId"),
	@NamedQuery(name = "MethodTypes.findByShortName", query = "SELECT m FROM MethodTypes m WHERE m.shortName = :shortName"),
	@NamedQuery(name = "MethodTypes.findByFullName", query = "SELECT m FROM MethodTypes m WHERE m.fullName = :fullName"),
	@NamedQuery(name = "MethodTypes.findByDateCreated", query = "SELECT m FROM MethodTypes m WHERE m.dateCreated = :dateCreated"),
	@NamedQuery(name = "MethodTypes.findByDeleted", query = "SELECT m FROM MethodTypes m WHERE m.deleted = :deleted")
})
@EntityListeners(DateUpdateListener.class)
public class MethodTypes implements Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 3383111202158692806L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "MethodTypeId")
	private Integer methodTypeId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 25)
	@Column(name = "ShortName")
	private String shortName;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "FullName")
	private String fullName;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Deleted")
	private boolean deleted;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "methodType")
	private Collection<Methods> methodsCollection;

	public MethodTypes() {
	}

	public MethodTypes(Integer methodTypeId) {
		this.methodTypeId = methodTypeId;
	}

	public MethodTypes(Integer methodTypeId, String shortName, String fullName, Date dateCreated, boolean deleted) {
		this.methodTypeId = methodTypeId;
		this.shortName = shortName;
		this.fullName = fullName;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
	}

	public Integer getMethodTypeId() {
		return methodTypeId;
	}

	public void setMethodTypeId(Integer methodTypeId) {
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

	@Override
	public Integer getId() {
		return getMethodTypeId();
	}

	@Override
	public void setId(Integer id) {
		setMethodTypeId(id);
	}

	@Override
	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public boolean getDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public Users getUserCreated() {
		return userCreated;
	}

	@Override
	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	@XmlTransient
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
		MethodTypes that = (MethodTypes) o;
		return getDeleted() == that.getDeleted() && Objects.equals(getMethodTypeId(), that.getMethodTypeId()) && Objects.equals(getShortName(), that.getShortName()) && Objects.equals(getFullName(), that.getFullName()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated()) && Objects.equals(getMethodsCollection(), that.getMethodsCollection());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMethodTypeId(), getShortName(), getFullName(), getDateCreated(), getDeleted(), getUserCreated(), getMethodsCollection());
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
