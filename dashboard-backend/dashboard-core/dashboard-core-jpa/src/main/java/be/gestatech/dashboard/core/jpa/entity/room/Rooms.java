package be.gestatech.dashboard.core.jpa.entity.room;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.plan.Plan;
import be.gestatech.dashboard.core.jpa.entity.schedule.Schedule;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User's room
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Rooms.TABLE_NAME)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
	@NamedQuery(name = "Rooms.findByRoomId", query = "SELECT r FROM Rooms r WHERE r.roomId = :roomId"),
	@NamedQuery(name = "Rooms.findByName", query = "SELECT r FROM Rooms r WHERE r.name = :name"),
	@NamedQuery(name = "Rooms.findByDescription", query = "SELECT r FROM Rooms r WHERE r.description = :description"),
	@NamedQuery(name = "Rooms.findByDateCreated", query = "SELECT r FROM Rooms r WHERE r.dateCreated = :dateCreated"),
	@NamedQuery(name = "Rooms.findByDeleted", query = "SELECT r FROM Rooms r WHERE r.deleted = :deleted")
})
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "ROOM_ID"))
public class Rooms extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -8052922608988382788L;

	public static final String TABLE_NAME = "ROOM";

	@Column(name = "ROOM_ID")
	private Long roomId;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "NAME")
	private String name;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ROOM")
	private Collection<Plan> planCollection;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ROOM")
	private Collection<Schedule> scheduleCollection;

	public Rooms() {
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Collection<Plan> getPlanCollection() {
		return planCollection;
	}

	public void setPlanCollection(Collection<Plan> planCollection) {
		this.planCollection = planCollection;
	}

	public Users getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	public Collection<Schedule> getScheduleCollection() {
		return scheduleCollection;
	}

	public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
		this.scheduleCollection = scheduleCollection;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Rooms)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Rooms rooms = (Rooms) o;
		return isDeleted() == rooms.isDeleted() && Objects.equals(getRoomId(), rooms.getRoomId()) && Objects.equals(getName(), rooms.getName()) && Objects.equals(getDescription(), rooms.getDescription()) && Objects.equals(getDateCreated(), rooms.getDateCreated()) && Objects.equals(getPlanCollection(), rooms.getPlanCollection()) && Objects.equals(getUserCreated(), rooms.getUserCreated()) && Objects.equals(getScheduleCollection(), rooms.getScheduleCollection());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getRoomId(), getName(), getDescription(), getDateCreated(), isDeleted(), getPlanCollection(), getUserCreated(), getScheduleCollection());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Rooms{");
		sb.append("roomId=").append(roomId);
		sb.append(", name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", planCollection=").append(planCollection);
		sb.append(", userCreated=").append(userCreated);
		sb.append(", scheduleCollection=").append(scheduleCollection);
		sb.append('}');
		return sb.toString();
	}
}