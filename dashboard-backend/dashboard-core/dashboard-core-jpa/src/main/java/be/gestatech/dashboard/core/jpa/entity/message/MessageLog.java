package be.gestatech.dashboard.core.jpa.entity.message;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.dashboard.core.jpa.entity.Identifiable;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes Log record for sent Message
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "MESSAGE_LOG")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class MessageLog implements Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 6292643524288361718L;

	@Id
	@Column(name = "MessageLogId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageLogId;

	@Basic
	@Column(name = "UId")
	private String uId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Status")
	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@JoinColumn(name = "Transaction", referencedColumnName = "TransactionLogId")
	@ManyToOne(optional = false)
	private TransactionLog transaction;

	@JoinColumn(name = "Recipient", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users recipient;

	@Size(max = 200)
	@Column(name = "Details")
	private String details;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Deleted")
	private boolean deleted;

	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;

	public MessageLog() {
	}

	public enum Status {
		NEW,
		SENT,
		WAITING,
		SENDING,
		PAUSED,
		CANCELED,
		DELIVERED,
		DELIVERY_ERROR,
		SEND_ERROR
	}


	public Integer getMessageLogId() {
		return messageLogId;
	}
	public void setMessageLogId(Integer messageLogId) {
		this.messageLogId = messageLogId;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public Users getRecipient() {
		return recipient;
	}

	public void setRecipient(Users recipient) {
		this.recipient = recipient;
	}

	public TransactionLog getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionLog transaction) {
		this.transaction = transaction;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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
	public Integer getId() {
		return messageLogId;
	}
	@Override
	public void setId(Integer id) {
		this.messageLogId = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MessageLog)) {
			return false;
		}
		MessageLog that = (MessageLog) o;
		return getDeleted() == that.getDeleted() && Objects.equals(getMessageLogId(), that.getMessageLogId()) && Objects.equals(getuId(), that.getuId()) && getStatus() == that.getStatus() && Objects.equals(getTransaction(), that.getTransaction()) && Objects.equals(getRecipient(), that.getRecipient()) && Objects.equals(getDetails(), that.getDetails()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMessageLogId(), getuId(), getStatus(), getTransaction(), getRecipient(), getDetails(), getDateCreated(), getDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MessageLog{");
		sb.append("messageLogId=").append(messageLogId);
		sb.append(", uId='").append(uId).append('\'');
		sb.append(", status=").append(status);
		sb.append(", transaction=").append(transaction);
		sb.append(", recipient=").append(recipient);
		sb.append(", details='").append(details).append('\'');
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}