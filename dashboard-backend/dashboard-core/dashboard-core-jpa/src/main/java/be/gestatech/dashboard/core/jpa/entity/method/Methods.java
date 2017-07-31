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
import be.gestatech.dashboard.core.jpa.entity.price.Prices;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User's method
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Methods.TABLE_NAME)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Method.findAll", query = "SELECT m FROM Methods m"),
	@NamedQuery(name = "Method.findByMethodId", query = "SELECT m FROM Methods m WHERE m.methodId = :methodId"),
	@NamedQuery(name = "Method.findByShortName", query = "SELECT m FROM Methods m WHERE m.shortName = :shortName"),
	@NamedQuery(name = "Method.findByFullName", query = "SELECT m FROM Methods m WHERE m.fullName = :fullName"),
	@NamedQuery(name = "Method.findByShortDescription", query = "SELECT m FROM Methods m WHERE m.shortDescription = :shortDescription"),
	@NamedQuery(name = "Method.findByFullDescription", query = "SELECT m FROM Methods m WHERE m.fullDescription = :fullDescription"),
	@NamedQuery(name = "Method.findByTimeInMinutes", query = "SELECT m FROM Methods m WHERE m.timeInMinutes = :timeInMinutes"),
	@NamedQuery(name = "Method.findByDateCreated", query = "SELECT m FROM Methods m WHERE m.dateCreated = :dateCreated"),
	@NamedQuery(name = "Method.findByDeleted", query = "SELECT m FROM Methods m WHERE m.deleted = :deleted")
})
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "METHOD_ID"))
public class Methods extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -1551959979458824639L;

	public static final String TABLE_NAME = "METHOD";

	@Column(name = "METHOD_ID")
	private Long methodId;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "SHORT_NAME")
	private String shortName;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "FULL_NAME")
	private String fullName;

	@Size(max = 100)
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	@Size(max = 300)
	@Column(name = "FULL_DESCRIPTION")
	private String fullDescription;

	@Basic(optional = false)
	@NotNull
	@Column(name = "TimeInMinutes")
	private int timeInMinutes;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DELETED")
	private boolean deleted;

	@OneToMany(mappedBy = "METHOD")
	private Collection<Prices> pricesCollection;

	@JoinColumn(name = "METHOD_TYPE", referencedColumnName = "METHOD_TYPE_ID")
	@ManyToOne(optional = false)
	private MethodTypes methodType;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	public Methods() {
	}

	public Long getMethodId() {
		return methodId;
	}

	public void setMethodId(Long methodId) {
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

	public Users getUserCreated() {
		return userCreated;
	}

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
		if (!super.equals(o)) {
			return false;
		}
		Methods methods = (Methods) o;
		return getTimeInMinutes() == methods.getTimeInMinutes() && isDeleted() == methods.isDeleted() && Objects.equals(getMethodId(), methods.getMethodId()) && Objects.equals(getShortName(), methods.getShortName()) && Objects.equals(getFullName(), methods.getFullName()) && Objects.equals(getShortDescription(), methods.getShortDescription()) && Objects.equals(getFullDescription(), methods.getFullDescription()) && Objects.equals(getDateCreated(), methods.getDateCreated()) && Objects.equals(getPricesCollection(), methods.getPricesCollection()) && Objects.equals(getMethodType(), methods.getMethodType()) && Objects.equals(getUserCreated(), methods.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getMethodId(), getShortName(), getFullName(), getShortDescription(), getFullDescription(), getTimeInMinutes(), getDateCreated(), isDeleted(), getPricesCollection(), getMethodType(), getUserCreated());
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