package be.gestatech.dashboard.core.jpa.entity.message;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes Log record for sent Message
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = MessageLog.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "MESSAGE_LOG_ID"))
public class MessageLog extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 6292643524288361718L;

	public static final String TABLE_NAME = "MESSAGE_LOG";

	@Column(name = "MESSAGE_LOG_ID")
	private Long messageLogId;

	@Basic
	@Column(name = "UID")
	private String uId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@JoinColumn(name = "TRANSACTION", referencedColumnName = "TRANSACTION_LOG_ID")
	@ManyToOne(optional = false)
	private TransactionLog transaction;

	@JoinColumn(name = "RECIPIENT", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users recipient;

	@Size(max = 200)
	@Column(name = "DETAILS")
	private String details;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DELETED")
	private boolean deleted;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	public MessageLog() {
	}

	public enum Status {
		NEW, SENT, WAITING, SENDING, PAUSED, CANCELED, DELIVERED, DELIVERY_ERROR, SEND_ERROR
	}

	public Long getMessageLogId() {
		return messageLogId;
	}

	public void setMessageLogId(Long messageLogId) {
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

	public TransactionLog getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionLog transaction) {
		this.transaction = transaction;
	}

	public Users getRecipient() {
		return recipient;
	}

	public void setRecipient(Users recipient) {
		this.recipient = recipient;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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

	public Users getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MessageLog)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		MessageLog that = (MessageLog) o;
		return isDeleted() == that.isDeleted() && Objects.equals(getMessageLogId(), that.getMessageLogId()) && Objects.equals(getuId(), that.getuId()) && getStatus() == that.getStatus() && Objects.equals(getTransaction(), that.getTransaction()) && Objects.equals(getRecipient(), that.getRecipient()) && Objects.equals(getDetails(), that.getDetails()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getMessageLogId(), getuId(), getStatus(), getTransaction(), getRecipient(), getDetails(), getDateCreated(), isDeleted(), getUserCreated());
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