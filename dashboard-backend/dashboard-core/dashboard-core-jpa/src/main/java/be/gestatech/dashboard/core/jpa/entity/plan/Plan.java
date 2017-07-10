package be.gestatech.dashboard.core.jpa.entity.plan;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.base.BaseEntity;
import be.gestatech.dashboard.core.jpa.entity.user.Users;
import be.gestatech.dashboard.core.jpa.entity.room.Rooms;

/**
 * Entity class Describes User's plan
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "PLAN")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
	@NamedQuery(name = "Plan.findByPlanId", query = "SELECT p FROM Plan p WHERE p.planId = :planId"),
	@NamedQuery(name = "Plan.findByDateTimeStart", query = "SELECT p FROM Plan p WHERE p.dateTimeStart = :dateTimeStart"),
	@NamedQuery(name = "Plan.findByDateTimeEnd", query = "SELECT p FROM Plan p WHERE p.dateTimeEnd = :dateTimeEnd"),
	@NamedQuery(name = "Plan.findByDescription", query = "SELECT p FROM Plan p WHERE p.description = :description"),
	@NamedQuery(name = "Plan.findByDateCreated", query = "SELECT p FROM Plan p WHERE p.dateCreated = :dateCreated"),
	@NamedQuery(name = "Plan.findByDeleted", query = "SELECT p FROM Plan p WHERE p.deleted = :deleted")
})
@EntityListeners(AuditEntityListener.class)
public class Plan extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 2391786817629544333L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "PlanId")
	private Integer planId;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateTimeStart")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTimeStart;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateTimeEnd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTimeEnd;
	@Size(max = 200)
	@Column(name = "Description")
	private String description;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Deleted")
	private boolean deleted;
	@JoinColumn(name = "Room", referencedColumnName = "RoomId")
	@ManyToOne(optional = false)
	private Rooms room;
	@JoinColumn(name = "Doctor", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users doctor;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;

	public Plan() {
	}

	public Plan(Integer planId) {
		this.planId = planId;
	}

	public Plan(Integer planId, Date dateTimeStart, Date dateTimeEnd, Date dateCreated, boolean deleted) {
		this.planId = planId;
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Date getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(Date dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public Date getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(Date dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}

	public Users getDoctor() {
		return doctor;
	}

	public void setDoctor(Users doctor) {
		this.doctor = doctor;
	}

	@Override
	public Integer getId() {
		return getPlanId();
	}

	@Override
	public void setId(Integer id) {
		setPlanId(id);
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Plan)) {
			return false;
		}
		Plan plan = (Plan) o;
		return getDeleted() == plan.getDeleted() && Objects.equals(getPlanId(), plan.getPlanId()) && Objects.equals(getDateTimeStart(), plan.getDateTimeStart()) && Objects.equals(getDateTimeEnd(), plan.getDateTimeEnd()) && Objects.equals(getDescription(), plan.getDescription()) && Objects.equals(getDateCreated(), plan.getDateCreated()) && Objects.equals(getRoom(), plan.getRoom()) && Objects.equals(getDoctor(), plan.getDoctor()) && Objects.equals(getUserCreated(), plan.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPlanId(), getDateTimeStart(), getDateTimeEnd(), getDescription(), getDateCreated(), getDeleted(), getRoom(), getDoctor(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Plan{");
		sb.append("planId=").append(planId);
		sb.append(", dateTimeStart=").append(dateTimeStart);
		sb.append(", dateTimeEnd=").append(dateTimeEnd);
		sb.append(", description='").append(description).append('\'');
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", room=").append(room);
		sb.append(", doctor=").append(doctor);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}
