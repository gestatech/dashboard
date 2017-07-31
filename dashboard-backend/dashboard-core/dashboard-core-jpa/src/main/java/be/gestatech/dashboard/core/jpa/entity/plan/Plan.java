package be.gestatech.dashboard.core.jpa.entity.plan;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.room.Rooms;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User's plan
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Plan.TABLE_NAME)
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
@AttributeOverride(name = "ID", column = @Column(name = "PLAN_ID"))
public class Plan extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 2391786817629544333L;

	public static final String TABLE_NAME = "PLAN";

	@Column(name = "PLAN_ID")
	private Long planId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "START_DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTimeStart;

	@Basic(optional = false)
	@NotNull
	@Column(name = "END_DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTimeEnd;

	@Size(max = 200)
	@Column(name = "DESCRIPTION")
	private String description;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DELETED")
	private boolean deleted;

	@JoinColumn(name = "ROOM", referencedColumnName = "ROOM_ID")
	@ManyToOne(optional = false)
	private Rooms room;

	@JoinColumn(name = "DOCTOR", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users doctor;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	public Plan() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Plan)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Plan plan = (Plan) o;
		return deleted == plan.deleted && Objects.equals(planId, plan.planId) && Objects.equals(dateTimeStart, plan.dateTimeStart) && Objects.equals(dateTimeEnd, plan.dateTimeEnd) && Objects.equals(description, plan.description) && Objects.equals(dateCreated, plan.dateCreated) && Objects.equals(room, plan.room) && Objects.equals(doctor, plan.doctor) && Objects.equals(userCreated, plan.userCreated);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), planId, dateTimeStart, dateTimeEnd, description, dateCreated, deleted, room, doctor, userCreated);
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
