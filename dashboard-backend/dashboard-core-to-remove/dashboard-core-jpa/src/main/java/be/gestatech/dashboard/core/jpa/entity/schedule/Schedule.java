package be.gestatech.dashboard.core.jpa.entity.schedule;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.method.Methods;
import be.gestatech.dashboard.core.jpa.entity.payment.Payments;
import be.gestatech.dashboard.core.jpa.entity.room.Rooms;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User's schedule
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Schedule.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "SCHEDULE_ID"))
public class Schedule extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 5182241502702844158L;

	public static final String TABLE_NAME = "SCHEDULE";

	@Column(name = "SCHEDULE_ID")
	private Long scheduleId;

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

	@JoinColumn(name = "METHOD", referencedColumnName = "METHOD_ID")
	@ManyToOne(optional = false)
	private Methods method;

	@JoinColumn(name = "ROOM", referencedColumnName = "ROOM_ID")
	@ManyToOne(optional = false)
	private Rooms room;

	@JoinColumn(name = "PATIENT", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users patient;

	@JoinColumn(name = "ASSISTANT", referencedColumnName = "USER_ID")
	@ManyToOne
	private Users assistant;

	@JoinColumn(name = "DOCTOR", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users doctor;

	@JoinColumn(name = "DOCTOR_DIRECTED", referencedColumnName = "USER_ID")
	@ManyToOne
	private Users doctorDirected;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	@JoinColumn(name = "PATIENT_SCHEDULE", referencedColumnName = "SCHEDULE_ID")
	@ManyToOne
	private Schedule parentSchedule;

	@OneToMany(mappedBy="SCHEDULE")
	private Collection<Payments> payments;

	public Schedule() {
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
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

	public void setAssistant(Users assistant) {
		this.assistant = assistant;
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

	public void setDoctorDirected(Users doctorDirected) {
		this.doctorDirected = doctorDirected;
	}

	public Users getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	public Schedule getParentSchedule() {
		return parentSchedule;
	}

	public void setParentSchedule(Schedule parentSchedule) {
		this.parentSchedule = parentSchedule;
	}

	public Collection<Payments> getPayments() {
		return payments;
	}

	public void setPayments(Collection<Payments> payments) {
		this.payments = payments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Schedule)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Schedule schedule = (Schedule) o;
		return isDeleted() == schedule.isDeleted() && Objects.equals(getScheduleId(), schedule.getScheduleId()) && Objects.equals(getDateTimeStart(), schedule.getDateTimeStart()) && Objects.equals(getDateTimeEnd(), schedule.getDateTimeEnd()) && Objects.equals(getDescription(), schedule.getDescription()) && Objects.equals(getDateCreated(), schedule.getDateCreated()) && Objects.equals(getMethod(), schedule.getMethod()) && Objects.equals(getRoom(), schedule.getRoom()) && Objects.equals(getPatient(), schedule.getPatient()) && Objects.equals(getAssistant(), schedule.getAssistant()) && Objects.equals(getDoctor(), schedule.getDoctor()) && Objects.equals(getDoctorDirected(), schedule.getDoctorDirected()) && Objects.equals(getUserCreated(), schedule.getUserCreated()) && Objects.equals(getParentSchedule(), schedule.getParentSchedule()) && Objects.equals(getPayments(), schedule.getPayments());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getScheduleId(), getDateTimeStart(), getDateTimeEnd(), getDescription(), getDateCreated(), isDeleted(), getMethod(), getRoom(), getPatient(), getAssistant(), getDoctor(), getDoctorDirected(), getUserCreated(), getParentSchedule(), getPayments());
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
