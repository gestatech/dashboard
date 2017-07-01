package be.gestatech.dashboard.core.jpa.entity.message;

import java.io.Serializable;
import java.util.Collection;
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
 * Entity class Describes log record for send messages transaction, when messages are sent to group of user
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "TRANSACTION_LOG")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class TransactionLog implements Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 3868003612731634590L;

	@Id
	@Column(name = "TransactionLogId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionLogId;

	@Basic
	@Column(name = "UId")
	private String uId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Status")
	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@JoinColumn(name = "MessageTemplate", referencedColumnName = "MessageTemplateId")
	@ManyToOne(optional = false)
	private MessageTemplate messageTemplate;

	@Size(max = 255)
	@Column(name = "Details")
	private String details;

	@Column(name = "RecipientsCount")
	private Integer recipientsCount;

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

	@OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER)
	private Collection<MessageLog> messageLogs;

	public TransactionLog() {
	}

	public enum Status {
		PROGRESS, SENT, //status for emails
		DELIVERED, //status for sms
		DELIVERY_ERROR, //status for sms
		SEND_ERROR, PAUSED,//status for sms
		CANCELED;//status for sms
	}

	public Integer getTransactionLogId() {
		return transactionLogId;
	}

	public void setTransactionLogId(Integer transactionLogId) {
		this.transactionLogId = transactionLogId;
	}

	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getRecipientsCount() {
		return recipientsCount;
	}

	public void setRecipientsCount(Integer recipientsCount) {
		this.recipientsCount = recipientsCount;
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
		return transactionLogId;
	}

	@Override
	public void setId(Integer id) {
		transactionLogId = id;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public Collection<MessageLog> getMessageLogs() {
		return messageLogs;
	}

	public void setMessageLogs(Collection<MessageLog> messageLogs) {
		this.messageLogs = messageLogs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TransactionLog)) {
			return false;
		}
		TransactionLog that = (TransactionLog) o;
		return getDeleted() == that.getDeleted() && Objects.equals(getTransactionLogId(), that.getTransactionLogId()) && Objects.equals(getuId(), that.getuId()) && getStatus() == that.getStatus() && Objects.equals(getMessageTemplate(), that.getMessageTemplate()) && Objects.equals(getDetails(), that.getDetails()) && Objects.equals(getRecipientsCount(), that.getRecipientsCount()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated()) && Objects.equals(getMessageLogs(), that.getMessageLogs());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTransactionLogId(), getuId(), getStatus(), getMessageTemplate(), getDetails(), getRecipientsCount(), getDateCreated(), getDeleted(), getUserCreated(), getMessageLogs());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TransactionLog{");
		sb.append("transactionLogId=").append(transactionLogId);
		sb.append(", uId='").append(uId).append('\'');
		sb.append(", status=").append(status);
		sb.append(", messageTemplate=").append(messageTemplate);
		sb.append(", details='").append(details).append('\'');
		sb.append(", recipientsCount=").append(recipientsCount);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append(", messageLogs=").append(messageLogs);
		sb.append('}');
		return sb.toString();
	}
}
