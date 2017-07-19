package be.gestatech.dashboard.core.jpa.entity.i18n;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes message in particular locale and type
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = MessageBundle.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "MESSAGE_BUNDLE_ID"))
public class MessageBundle extends AbstractPersistable<Long> implements Serializable {

	private static final long serialVersionUID = 6574392255017080137L;

	public static final String TABLE_NAME = "MESSAGE_BUNDLE";

	@Column(name = "MESSAGE_BUNDLE_ID")
	private Integer messageBundleId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "MESSAGE_KEY")
	private String messageKey;
	@Basic(optional = false)
	@NotNull
	@Column(name = "VALUE")
	private String value;
	@Basic(optional = false)
	@NotNull
	@Column(name = "TYPE")
	@Enumerated(EnumType.ORDINAL)
	private Type type;
	@ManyToOne
	@JoinColumn(name = "LOCALE")
	private Locale locale;

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

	public MessageBundle() {
	}

	public enum Type {
		MESSAGE, CONFIG, MAPPING
	}

	public Integer getMessageBundleId() {
		return messageBundleId;
	}

	public void setMessageBundleId(Integer messageBundleId) {
		this.messageBundleId = messageBundleId;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
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
		if (!(o instanceof MessageBundle)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		MessageBundle that = (MessageBundle) o;
		return isDeleted() == that.isDeleted() && Objects.equals(getMessageBundleId(), that.getMessageBundleId()) && Objects.equals(getMessageKey(), that.getMessageKey()) && Objects.equals(getValue(), that.getValue()) && getType() == that.getType() && Objects.equals(getLocale(), that.getLocale()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getMessageBundleId(), getMessageKey(), getValue(), getType(), getLocale(), getDateCreated(), isDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MessageBundle{");
		sb.append("messageBundleId=").append(messageBundleId);
		sb.append(", messageKey='").append(messageKey).append('\'');
		sb.append(", value='").append(value).append('\'');
		sb.append(", type=").append(type);
		sb.append(", locale=").append(locale);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}