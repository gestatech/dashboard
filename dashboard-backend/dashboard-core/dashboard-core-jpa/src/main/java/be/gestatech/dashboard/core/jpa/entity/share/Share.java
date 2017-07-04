package be.gestatech.dashboard.core.jpa.entity.share;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.dashboard.core.jpa.entity.BaseEntity;
import be.gestatech.dashboard.core.jpa.entity.method.Methods;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "SHARE")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class Share extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = -8834587744671614251L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShareId")
	private Integer shareId;
	@Column(name = "SalaryDoctor")
	private Float salaryDoctor;
	@Column(name = "SalaryAssistant")
	private Float salaryAssistant;
	@Max(value=100)  @Min(value=0)
	@Column(name = "PercentageDoctor")
	private Float percentageDoctor;
	@Max(value=100)  @Min(value=0)
	@Column(name = "PercentageAssistant")
	private Float percentageAssistant;
	@Basic(optional = false)
	@NotNull
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Deleted")
	private boolean deleted;
	@OrderColumn(name="Share")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "ShareHasMethods", joinColumns={@JoinColumn(name="Share", referencedColumnName = "ShareId")},
			inverseJoinColumns={@JoinColumn(name="Method", referencedColumnName="MethodId")})
	private Collection<Methods> methods;
	@OrderColumn(name="Share")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "ShareHasDoctors", joinColumns={@JoinColumn(name="Share", referencedColumnName = "ShareId")},
			inverseJoinColumns={@JoinColumn(name="Doctor", referencedColumnName="UserId")})
	private Collection<Users> doctors;
	@OrderColumn(name="Share")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "ShareHasAssistants", joinColumns={@JoinColumn(name="Share", referencedColumnName = "ShareId")},
			inverseJoinColumns={@JoinColumn(name="Assistant", referencedColumnName="UserId")})
	private Collection<Users> assistants;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;

	public Share() {
	}

	public Share(Integer shareId) {
		this.shareId = shareId;
	}

	public Share(Integer shareId, Date date, Date dateCreated, boolean deleted) {
		this.shareId = shareId;
		this.date = date;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
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

	@Override
	public Integer getId() {
		return getShareId();
	}

	@Override
	public void setId(Integer id) {
		setShareId(id);
	}

	public Integer getShareId() {
		return shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	public Float getSalaryDoctor() {
		return salaryDoctor;
	}

	public void setSalaryDoctor(Float salaryDoctor) {
		this.salaryDoctor = salaryDoctor;
	}

	public Float getSalaryAssistant() {
		return salaryAssistant;
	}

	public void setSalaryAssistant(Float salaryAssistant) {
		this.salaryAssistant = salaryAssistant;
	}

	public Float getPercentageDoctor() {
		return percentageDoctor;
	}

	public void setPercentageDoctor(Float persentageDoctor) {
		this.percentageDoctor = persentageDoctor;
	}

	public Float getPercentageAssistant() {
		return percentageAssistant;
	}

	public void setPercentageAssistant(Float percentageAssistant) {
		this.percentageAssistant = percentageAssistant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Collection<Methods> getMethods() {
		return methods;
	}

	public void setMethods(Collection<Methods> methods) {
		this.methods = methods;
	}

	public Collection<Users> getDoctors() {
		return doctors;
	}

	public void setDoctors(Collection<Users> doctors) {
		this.doctors = doctors;
	}

	public Collection<Users> getAssistants() {
		return assistants;
	}

	public void setAssistants(Collection<Users> assistants) {
		this.assistants = assistants;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Share)) {
			return false;
		}
		Share share = (Share) o;
		return getDeleted() == share.getDeleted() && Objects.equals(getShareId(), share.getShareId()) && Objects.equals(getSalaryDoctor(), share.getSalaryDoctor()) && Objects.equals(getSalaryAssistant(), share.getSalaryAssistant()) && Objects.equals(getPercentageDoctor(), share.getPercentageDoctor()) && Objects.equals(getPercentageAssistant(), share.getPercentageAssistant()) && Objects.equals(getDate(), share.getDate()) && Objects.equals(getDateCreated(), share.getDateCreated()) && Objects.equals(getMethods(), share.getMethods()) && Objects.equals(getDoctors(), share.getDoctors()) && Objects.equals(getAssistants(), share.getAssistants()) && Objects.equals(getUserCreated(), share.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getShareId(), getSalaryDoctor(), getSalaryAssistant(), getPercentageDoctor(), getPercentageAssistant(), getDate(), getDateCreated(), getDeleted(), getMethods(), getDoctors(), getAssistants(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Share{");
		sb.append("shareId=").append(shareId);
		sb.append(", salaryDoctor=").append(salaryDoctor);
		sb.append(", salaryAssistant=").append(salaryAssistant);
		sb.append(", percentageDoctor=").append(percentageDoctor);
		sb.append(", percentageAssistant=").append(percentageAssistant);
		sb.append(", date=").append(date);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", methods=").append(methods);
		sb.append(", doctors=").append(doctors);
		sb.append(", assistants=").append(assistants);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}
