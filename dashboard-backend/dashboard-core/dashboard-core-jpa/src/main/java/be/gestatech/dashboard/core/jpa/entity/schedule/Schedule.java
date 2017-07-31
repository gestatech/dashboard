package be.gestatech.dashboard.core.jpa.entity.schedule;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.base.BaseEntity;
import be.gestatech.dashboard.core.jpa.entity.method.Methods;
import be.gestatech.dashboard.core.jpa.entity.payment.Payments;
import be.gestatech.dashboard.core.jpa.entity.room.Rooms;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User's schedule
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "SCHEDULE")
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
public class Schedule extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 5182241502702844158L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ScheduleId")
	private Integer scheduleId;
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
	@JoinColumn(name = "Method", referencedColumnName = "MethodId")
	@ManyToOne(optional = false)
	private Methods method;
	@JoinColumn(name = "Room", referencedColumnName = "RoomId")
	@ManyToOne(optional = false)
	private Rooms room;
	@JoinColumn(name = "Patient", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users patient;
	@JoinColumn(name = "Assistant", referencedColumnName = "UserId")
	@ManyToOne
	private Users assistant;
	@JoinColumn(name = "Doctor", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users doctor;
	@JoinColumn(name = "DoctorDirected", referencedColumnName = "UserId")
	@ManyToOne
	private Users doctorDirected;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;
	@JoinColumn(name = "ParentSchedule", referencedColumnName = "ScheduleId")
	@ManyToOne
	private Schedule parentSchedule;

	@OneToMany(mappedBy="schedule")
	private Collection<Payments> payments;

	public Schedule() {
	}

	public Schedule(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Schedule(Integer scheduleId, Date dateTimeStart, Date dateTimeEnd, Date dateCreated, boolean deleted) {
		this.scheduleId = scheduleId;
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
	}

	public Schedule(Schedule schedule, Methods method, Date startTime, Users patient, Rooms room, Users authorizedUser ){
		this.doctor = schedule.getDoctor();
		this.patient = patient;
		this.assistant = schedule.getAssistant();
		this.doctorDirected = schedule.getDoctorDirected();
		this.room = room;
		this.method = method;
		this.dateTimeStart = startTime;
		this.dateTimeEnd = new Date(startTime.getTime() + (method.getTimeInMinutes() * 60L * 1000L));
		this.dateCreated = new Date();
		this.userCreated = authorizedUser;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
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

	public Schedule getParentSchedule() {
		return parentSchedule;
	}

	public void setParentSchedule(Schedule parentSchedule) {
		this.parentSchedule = parentSchedule;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Methods getMethod() {
		return method;
	}

	public void setMethod(Methods method) {
		this.method = method;
	}

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}

	public Users getPatient() {
		return patient;
	}

	public void setPatient(Users patient) {
		this.patient = patient;
	}

	public Users getAssistant() {
		return assistant;
	}

	public void setAssistant(Users assistent) {
		this.assistant = assistent;
	}

	public Users getDoctor() {
		return doctor;
	}

	public void setDoctor(Users doctor) {
		this.doctor = doctor;
	}

	public Users getDoctorDirected() {
		return doctorDirected;
	}

	public Collection<Payments> getPayments() {
		return payments;
	}

	public void setPayments(Collection<Payments> payments) {
		this.payments = payments;
	}

	public void setDoctorDirected(Users doctorDirected) {
		this.doctorDirected = doctorDirected;
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
	public Integer getId() {
		return getScheduleId();
	}

	@Override
	public void setId(Integer id) {
		setScheduleId(id);
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
		if (!(o instanceof Schedule)) {
			return false;
		}
		Schedule schedule = (Schedule) o;
		return getDeleted() == schedule.getDeleted() && Objects.equals(getScheduleId(), schedule.getScheduleId()) && Objects.equals(getDateTimeStart(), schedule.getDateTimeStart()) && Objects.equals(getDateTimeEnd(), schedule.getDateTimeEnd()) && Objects.equals(getDescription(), schedule.getDescription()) && Objects.equals(getDateCreated(), schedule.getDateCreated()) && Objects.equals(getMethod(), schedule.getMethod()) && Objects.equals(getRoom(), schedule.getRoom()) && Objects.equals(getPatient(), schedule.getPatient()) && Objects.equals(getAssistant(), schedule.getAssistant()) && Objects.equals(getDoctor(), schedule.getDoctor()) && Objects.equals(getDoctorDirected(), schedule.getDoctorDirected()) && Objects.equals(getUserCreated(), schedule.getUserCreated()) && Objects.equals(getParentSchedule(), schedule.getParentSchedule()) && Objects.equals(getPayments(), schedule.getPayments());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getScheduleId(), getDateTimeStart(), getDateTimeEnd(), getDescription(), getDateCreated(), getDeleted(), getMethod(), getRoom(), getPatient(), getAssistant(), getDoctor(), getDoctorDirected(), getUserCreated(), getParentSchedule(), getPayments());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Schedule{");
		sb.append("scheduleId=").append(scheduleId);
		sb.append(", dateTimeStart=").append(dateTimeStart);
		sb.append(", dateTimeEnd=").append(dateTimeEnd);
		sb.append(", description='").append(description).append('\'');
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", method=").append(method);
		sb.append(", room=").append(room);
		sb.append(", patient=").append(patient);
		sb.append(", assistant=").append(assistant);
		sb.append(", doctor=").append(doctor);
		sb.append(", doctorDirected=").append(doctorDirected);
		sb.append(", userCreated=").append(userCreated);
		sb.append(", parentSchedule=").append(parentSchedule);
		sb.append(", payments=").append(payments);
		sb.append('}');
		return sb.toString();
	}
}
