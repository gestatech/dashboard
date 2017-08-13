package be.gestatech.dashboard.core.jpa.entity.share;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.method.Methods;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Share.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "SHARE_ID"))
public class Share extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -8834587744671614251L;

	public static final String TABLE_NAME = "SHARE";

	@Column(name = "SHARE_ID")
	private Long shareId;

	@Column(name = "SALARY_DOCTOR")
	private Float salaryDoctor;

	@Column(name = "SALARY_ASSISTANT")
	private Float salaryAssistant;

	@Max(value=100)  @Min(value=0)
	@Column(name = "PERCENTAGE_DOCTOR")
	private Float percentageDoctor;

	@Max(value=100)  @Min(value=0)
	@Column(name = "PERCENTAGE_ASSISTANT")
	private Float percentageAssistant;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATE")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DELETED")
	private boolean deleted;

	@OrderColumn(name="SHARE")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "SHARE_HAS_METHODS", joinColumns={@JoinColumn(name="SHARE", referencedColumnName = "SHARE_ID")},
			inverseJoinColumns={@JoinColumn(name="METHOD", referencedColumnName="METHOD_ID")})
	private Collection<Methods> methods;

	@OrderColumn(name="SHARE")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "SHARE_HAS_DOCTORS", joinColumns={@JoinColumn(name="SHARE", referencedColumnName = "SHARE_ID")},
			inverseJoinColumns={@JoinColumn(name="DOCTOR", referencedColumnName="USER_ID")})
	private Collection<Users> doctors;

	@OrderColumn(name="Share")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "ShareHasAssistants", joinColumns={@JoinColumn(name="SHARE", referencedColumnName = "SHARE_ID")},
			inverseJoinColumns={@JoinColumn(name="ASSISTANT", referencedColumnName="USER_ID")})
	private Collection<Users> assistants;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	public Share() {
	}

	public Long getShareId() {
		return shareId;
	}

	public void setShareId(Long shareId) {
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

	public void setPercentageDoctor(Float percentageDoctor) {
		this.percentageDoctor = percentageDoctor;
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
		if (!(o instanceof Share)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Share share = (Share) o;
		return isDeleted() == share.isDeleted() && Objects.equals(getShareId(), share.getShareId()) && Objects.equals(getSalaryDoctor(), share.getSalaryDoctor()) && Objects.equals(getSalaryAssistant(), share.getSalaryAssistant()) && Objects.equals(getPercentageDoctor(), share.getPercentageDoctor()) && Objects.equals(getPercentageAssistant(), share.getPercentageAssistant()) && Objects.equals(getDate(), share.getDate()) && Objects.equals(getDateCreated(), share.getDateCreated()) && Objects.equals(getMethods(), share.getMethods()) && Objects.equals(getDoctors(), share.getDoctors()) && Objects.equals(getAssistants(), share.getAssistants()) && Objects.equals(getUserCreated(), share.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getShareId(), getSalaryDoctor(), getSalaryAssistant(), getPercentageDoctor(), getPercentageAssistant(), getDate(), getDateCreated(), isDeleted(), getMethods(), getDoctors(), getAssistants(), getUserCreated());
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
