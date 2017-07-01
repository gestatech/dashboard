package be.gestatech.dashboard.core.jpa.entity.method;

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
import be.gestatech.dashboard.core.jpa.entity.user.Users;
import be.gestatech.dashboard.core.jpa.entity.Identifiable;
import be.gestatech.dashboard.core.jpa.entity.price.Prices;

/**
 * Entity class Describes User's method
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "METHOD")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Methods.findAll", query = "SELECT m FROM Methods m"),
	@NamedQuery(name = "Methods.findByMethodId", query = "SELECT m FROM Methods m WHERE m.methodId = :methodId"),
	@NamedQuery(name = "Methods.findByShortName", query = "SELECT m FROM Methods m WHERE m.shortName = :shortName"),
	@NamedQuery(name = "Methods.findByFullName", query = "SELECT m FROM Methods m WHERE m.fullName = :fullName"),
	@NamedQuery(name = "Methods.findByShortDescription", query = "SELECT m FROM Methods m WHERE m.shortDescription = :shortDescription"),
	@NamedQuery(name = "Methods.findByFullDescription", query = "SELECT m FROM Methods m WHERE m.fullDescription = :fullDescription"),
	@NamedQuery(name = "Methods.findByTimeInMinutes", query = "SELECT m FROM Methods m WHERE m.timeInMinutes = :timeInMinutes"),
	@NamedQuery(name = "Methods.findByDateCreated", query = "SELECT m FROM Methods m WHERE m.dateCreated = :dateCreated"),
	@NamedQuery(name = "Methods.findByDeleted", query = "SELECT m FROM Methods m WHERE m.deleted = :deleted")
})
@EntityListeners(DateUpdateListener.class)
public class Methods implements Serializable,Identifiable<Integer> {

	private static final long serialVersionUID = -1551959979458824639L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MethodId")
	private Integer methodId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "ShortName")
	private String shortName;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "FullName")
	private String fullName;
	@Size(max = 100)
	@Column(name = "ShortDescription")
	private String shortDescription;
	@Size(max = 300)
	@Column(name = "FullDescription")
	private String fullDescription;
	@Basic(optional = false)
	@NotNull
	@Column(name = "TimeInMinutes")
	private int timeInMinutes;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Deleted")
	private boolean deleted;

	@OneToMany(mappedBy = "method")
	private Collection<Prices> pricesCollection;

	@JoinColumn(name = "MethodType", referencedColumnName = "MethodTypeId")
	@ManyToOne(optional = false)
	private MethodTypes methodType;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;

	public Methods() {
	}

	public Methods(Integer methodId) {
		this.methodId = methodId;
	}

	public Methods(Integer methodId, String shortName, String fullName, int timeInMinutes, Date dateCreated, boolean deleted) {
		this.methodId = methodId;
		this.shortName = shortName;
		this.fullName = fullName;
		this.timeInMinutes = timeInMinutes;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
	}

	public Integer getMethodId() {
		return methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public int getTimeInMinutes() {
		return timeInMinutes;
	}

	public void setTimeInMinutes(int timeInMinutes) {
		this.timeInMinutes = timeInMinutes;
	}

	@Override
	public Integer getId() {
		return getMethodId();
	}

	@Override
	public void setId(Integer id) {
		setMethodId(id);
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

	@XmlTransient
	public Collection<Prices> getPricesCollection() {
		return pricesCollection;
	}

	public void setPricesCollection(Collection<Prices> pricesCollection) {
		this.pricesCollection = pricesCollection;
	}

	public MethodTypes getMethodType() {
		return methodType;
	}

	public void setMethodType(MethodTypes methodType) {
		this.methodType = methodType;
	}
	@Override
	public Users getUserCreated() {
		return userCreated;
	}
	@Override
	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Methods)) {
			return false;
		}
		Methods methods = (Methods) o;
		return getTimeInMinutes() == methods.getTimeInMinutes() && getDeleted() == methods.getDeleted() && Objects.equals(getMethodId(), methods.getMethodId()) && Objects.equals(getShortName(), methods.getShortName()) && Objects.equals(getFullName(), methods.getFullName()) && Objects.equals(getShortDescription(), methods.getShortDescription()) && Objects.equals(getFullDescription(), methods.getFullDescription()) && Objects.equals(getDateCreated(), methods.getDateCreated()) && Objects.equals(getPricesCollection(), methods.getPricesCollection()) && Objects.equals(getMethodType(), methods.getMethodType()) && Objects.equals(getUserCreated(), methods.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMethodId(), getShortName(), getFullName(), getShortDescription(), getFullDescription(), getTimeInMinutes(), getDateCreated(), getDeleted(), getPricesCollection(), getMethodType(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Methods{");
		sb.append("methodId=").append(methodId);
		sb.append(", shortName='").append(shortName).append('\'');
		sb.append(", fullName='").append(fullName).append('\'');
		sb.append(", shortDescription='").append(shortDescription).append('\'');
		sb.append(", fullDescription='").append(fullDescription).append('\'');
		sb.append(", timeInMinutes=").append(timeInMinutes);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", pricesCollection=").append(pricesCollection);
		sb.append(", methodType=").append(methodType);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}