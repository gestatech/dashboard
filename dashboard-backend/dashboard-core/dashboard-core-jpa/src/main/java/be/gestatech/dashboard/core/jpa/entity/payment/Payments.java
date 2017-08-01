package be.gestatech.dashboard.core.jpa.entity.payment;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.schedule.Schedule;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Payments.TABLE_NAME)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p"),
	@NamedQuery(name = "Payments.findByPaymentId", query = "SELECT p FROM Payments p WHERE p.paymentId = :paymentId"),
	@NamedQuery(name = "Payments.findByDataTime", query = "SELECT p FROM Payments p WHERE p.dateTime = :dataTime"),
	@NamedQuery(name = "Payments.findByTotal", query = "SELECT p FROM Payments p WHERE p.total = :total"),
	@NamedQuery(name = "Payments.findByDescription", query = "SELECT p FROM Payments p WHERE p.description = :description"),
	@NamedQuery(name = "Payments.findByDateCreated", query = "SELECT p FROM Payments p WHERE p.dateCreated = :dateCreated"),
	@NamedQuery(name = "Payments.findByDeleted", query = "SELECT p FROM Payments p WHERE p.deleted = :deleted")
})
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "PAYMENT_ID"))
public class Payments extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -8285370076079148502L;

	public static final String TABLE_NAME = "PAYMENT";

	@Column(name = "PAYMENT_ID")
	private Long paymentId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	@Basic(optional = false)
	@NotNull
	@Column(name = "TOTAL")
	private float total;

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

	@JoinColumn(name = "SCHEDULE", referencedColumnName = "SCHEDULE_ID")
	@ManyToOne
	private Schedule schedule;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	@JoinColumn(name = "RECIPIENT", referencedColumnName = "USER_ID")
	@ManyToOne
	private Users recipient;

	public Payments() {
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
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

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Users getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	public Users getRecipient() {
		return recipient;
	}

	public void setRecipient(Users recipient) {
		this.recipient = recipient;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Payments)) {
			return false;
		}
		Payments payments = (Payments) o;
		return Float.compare(payments.getTotal(), getTotal()) == 0 && isDeleted() == payments.isDeleted() && Objects.equals(getPaymentId(), payments.getPaymentId()) && Objects.equals(getDateTime(), payments.getDateTime()) && Objects.equals(getDescription(), payments.getDescription()) && Objects.equals(getDateCreated(), payments.getDateCreated()) && Objects.equals(getSchedule(), payments.getSchedule()) && Objects.equals(getUserCreated(), payments.getUserCreated()) && Objects.equals(getRecipient(), payments.getRecipient());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPaymentId(), getDateTime(), getTotal(), getDescription(), getDateCreated(), isDeleted(), getSchedule(), getUserCreated(), getRecipient());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Payments{");
		sb.append("paymentId=").append(paymentId);
		sb.append(", dateTime=").append(dateTime);
		sb.append(", total=").append(total);
		sb.append(", description='").append(description).append('\'');
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", schedule=").append(schedule);
		sb.append(", userCreated=").append(userCreated);
		sb.append(", recipient=").append(recipient);
		sb.append('}');
		return sb.toString();
	}
}
