package be.gestatech.ecosytem.entity.payment;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.ecosytem.entity.Identifiable;
import be.gestatech.ecosytem.entity.schedule.Schedule;
import be.gestatech.ecosytem.entity.user.Users;

/**
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "PAYMENT")
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
@EntityListeners(DateUpdateListener.class)
public class Payments implements Serializable,Identifiable<Integer> {

	private static final long serialVersionUID = -8285370076079148502L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentId")
	private Integer paymentId;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Total")
	private float total;
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
	@JoinColumn(name = "Schedule", referencedColumnName = "ScheduleId")
	@ManyToOne
	private Schedule schedule;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;
	@JoinColumn(name = "Recipient", referencedColumnName = "UserId")
	@ManyToOne
	private Users recipient;

	public Payments() {
	}

	public Payments(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Payments(Integer paymentId, Date dateTime, float total, Date dateCreated, boolean deleted) {
		this.paymentId = paymentId;
		this.dateTime = dateTime;
		this.total = total;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dataTime) {
		this.dateTime = dataTime;
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

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Users getRecipient() {
		return recipient;
	}

	public void setRecipient(Users recipient) {
		this.recipient = recipient;
	}

	@Override
	public Integer getId() {
		return getPaymentId();
	}

	@Override
	public void setId(Integer id) {
		setPaymentId(id);
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Payments)) {
			return false;
		}
		Payments payments = (Payments) o;
		return Float.compare(payments.getTotal(), getTotal()) == 0 && getDeleted() == payments.getDeleted() && Objects.equals(getPaymentId(), payments.getPaymentId()) && Objects.equals(getDateTime(), payments.getDateTime()) && Objects.equals(getDescription(), payments.getDescription()) && Objects.equals(getDateCreated(), payments.getDateCreated()) && Objects.equals(getSchedule(), payments.getSchedule()) && Objects.equals(getUserCreated(), payments.getUserCreated()) && Objects.equals(getRecipient(), payments.getRecipient());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPaymentId(), getDateTime(), getTotal(), getDescription(), getDateCreated(), getDeleted(), getSchedule(), getUserCreated(), getRecipient());
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
