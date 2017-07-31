package be.gestatech.dashboard.core.jpa.entity.message;

import java.util.Collection;
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
 * Entity class Describes log record for send messages transaction, when messages are sent to group of user
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = TransactionLog.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "TRANSACTION_LOG_ID"))
public class TransactionLog extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 3868003612731634590L;

	public static final String TABLE_NAME = "TRANSACTION_LOG";

	@Column(name = "TRANSACTION_LOG_ID")
	private Long transactionLogId;

	@Basic
	@Column(name = "UID")
	private String uId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@JoinColumn(name = "MESSAGE_TEMPLATE", referencedColumnName = "MESSAGE_TEMPLATE_ID")
	@ManyToOne(optional = false)
	private MessageTemplate messageTemplate;

	@Size(max = 255)
	@Column(name = "DETATILS")
	private String details;

	@Column(name = "RECIPIENTS_COUNT")
	private Integer recipientsCount;

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

	public Long getTransactionLogId() {
		return transactionLogId;
	}

	public void setTransactionLogId(Long transactionLogId) {
		this.transactionLogId = transactionLogId;
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

	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
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
		return isDeleted() == that.isDeleted() && Objects.equals(getTransactionLogId(), that.getTransactionLogId()) && Objects.equals(getuId(), that.getuId()) && getStatus() == that.getStatus() && Objects.equals(getMessageTemplate(), that.getMessageTemplate()) && Objects.equals(getDetails(), that.getDetails()) && Objects.equals(getRecipientsCount(), that.getRecipientsCount()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated()) && Objects.equals(getMessageLogs(), that.getMessageLogs());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTransactionLogId(), getuId(), getStatus(), getMessageTemplate(), getDetails(), getRecipientsCount(), getDateCreated(), isDeleted(), getUserCreated(), getMessageLogs());
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
