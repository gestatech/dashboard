package be.gestatech.dashboard.core.jpa.entity.payment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.schedule.Schedule;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "PAYMENT")
@XmlRootElement
//@NamedQueries({
//	@NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p"),
//	@NamedQuery(name = "Payments.findByPaymentId", query = "SELECT p FROM Payments p WHERE p.paymentId = :paymentId"),
//	@NamedQuery(name = "Payments.findByDataTime", query = "SELECT p FROM Payments p WHERE p.dateTime = :dataTime"),
//	@NamedQuery(name = "Payments.findByTotal", query = "SELECT p FROM Payments p WHERE p.total = :total"),
//	@NamedQuery(name = "Payments.findByDescription", query = "SELECT p FROM Payments p WHERE p.description = :description"),
//	@NamedQuery(name = "Payments.findByDateCreated", query = "SELECT p FROM Payments p WHERE p.dateCreated = :dateCreated"),
//	@NamedQuery(name = "Payments.findByDeleted", query = "SELECT p FROM Payments p WHERE p.deleted = :deleted")
//})
@EntityListeners(AuditEntityListener.class)
public class Payments implements Serializable {

	private static final long serialVersionUID = -8285370076079148502L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentId")
	private Long paymentId;
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

}
