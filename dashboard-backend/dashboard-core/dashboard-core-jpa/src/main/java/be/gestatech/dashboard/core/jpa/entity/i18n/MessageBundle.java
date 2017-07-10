package be.gestatech.dashboard.core.jpa.entity.i18n;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.user.Users;
import be.gestatech.dashboard.core.jpa.entity.base.BaseEntity;

/**
 * Entity class Describes message in particular locale and type
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "MESSAGE_BUNDLE")
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
public class MessageBundle extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 6574392255017080137L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MessageBundleId")
	private Integer messageBundleId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "MessageKey")
	private String messageKey;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Value")
	private String value;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Type")
	@Enumerated(EnumType.ORDINAL)
	private Type type;
	@ManyToOne
	@JoinColumn(name = "Locale")
	private Locale locale;

	@Basic(optional = false)
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Basic(optional = false)
	@Column(name = "Deleted")
	private boolean deleted;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
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

	public void setMessageKey(String key) {
		this.messageKey = key;
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

	@Override
	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isDeleted() {
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
		return getMessageBundleId();
	}

	@Override
	public void setId(Integer id) {
		setMessageBundleId(id);
	}

	@Override
	public boolean getDeleted() {
		return deleted;
	}

	@Override
	public int compareTo(MessageBundle bundle) {
		int result = -1;
		if (Objects.nonNull(bundle)) {
			result = messageKey.compareTo(bundle.getMessageKey());
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MessageBundle)) {
			return false;
		}
		MessageBundle that = (MessageBundle) o;
		return isDeleted() == that.isDeleted() && Objects.equals(getMessageBundleId(), that.getMessageBundleId()) && Objects.equals(getMessageKey(), that.getMessageKey()) && Objects.equals(getValue(), that.getValue()) && getType() == that.getType() && Objects.equals(getLocale(), that.getLocale()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMessageBundleId(), getMessageKey(), getValue(), getType(), getLocale(), getDateCreated(), isDeleted(), getUserCreated());
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