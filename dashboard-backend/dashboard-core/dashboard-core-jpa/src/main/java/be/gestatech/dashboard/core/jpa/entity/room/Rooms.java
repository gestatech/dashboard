package be.gestatech.dashboard.core.jpa.entity.room;

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
import be.gestatech.dashboard.core.jpa.entity.BaseEntity;
import be.gestatech.dashboard.core.jpa.entity.plan.Plan;
import be.gestatech.dashboard.core.jpa.entity.schedule.Schedule;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User's room
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "ROOM")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
	@NamedQuery(name = "Rooms.findByRoomId", query = "SELECT r FROM Rooms r WHERE r.roomId = :roomId"),
	@NamedQuery(name = "Rooms.findByName", query = "SELECT r FROM Rooms r WHERE r.name = :name"),
	@NamedQuery(name = "Rooms.findByDescription", query = "SELECT r FROM Rooms r WHERE r.description = :description"),
	@NamedQuery(name = "Rooms.findByDateCreated", query = "SELECT r FROM Rooms r WHERE r.dateCreated = :dateCreated"),
	@NamedQuery(name = "Rooms.findByDeleted", query = "SELECT r FROM Rooms r WHERE r.deleted = :deleted")
})
@EntityListeners(DateUpdateListener.class)
public class Rooms extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = -8052922608988382788L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "RoomId")
	private Integer roomId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "Name")
	private String name;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
	private Collection<Plan> planCollection;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
	private Collection<Schedule> scheduleCollection;

	public Rooms() {
	}

	public Rooms(Integer roomId) {
		this.roomId = roomId;
	}

	public Rooms(Integer roomId, String name, Date dateCreated, boolean deleted) {
		this.roomId = roomId;
		this.name = name;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
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

	@XmlTransient
	public Collection<Schedule> getScheduleCollection() {
		return scheduleCollection;
	}

	public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
		this.scheduleCollection = scheduleCollection;
	}

	@XmlTransient
	public Collection<Plan> getPlanCollection() {
		return planCollection;
	}

	public void setPlanCollection(Collection<Plan> planCollection) {
		this.planCollection = planCollection;
	}

	@Override
	public Integer getId() {
		return getRoomId();
	}

	@Override
	public void setId(Integer id) {
		setRoomId(id);
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + (deleted ? 1231 : 1237);
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		result = prime * result
				+ ((userCreated == null) ? 0 : userCreated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Rooms)) {
			return false;
		}
		Rooms rooms = (Rooms) o;
		return getDeleted() == rooms.getDeleted() && Objects.equals(getRoomId(), rooms.getRoomId()) && Objects.equals(getName(), rooms.getName()) && Objects.equals(getDescription(), rooms.getDescription()) && Objects.equals(getDateCreated(), rooms.getDateCreated()) && Objects.equals(getPlanCollection(), rooms.getPlanCollection()) && Objects.equals(getUserCreated(), rooms.getUserCreated()) && Objects.equals(getScheduleCollection(), rooms.getScheduleCollection());
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