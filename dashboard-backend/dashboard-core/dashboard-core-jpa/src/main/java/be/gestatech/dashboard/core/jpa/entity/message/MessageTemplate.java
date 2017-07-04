package be.gestatech.dashboard.core.jpa.entity.message;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.dashboard.core.jpa.entity.BaseEntity;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes Message content
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "MESSAGE_TEMPLATE")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class MessageTemplate extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = -8874253374384369771L;

	@Id
	@Column(name = "MessageTemplateId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageTemplateId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Name")
	private String name;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Content", length=5000)
	private String content;

	@Basic
	@Column(name = "Description")
	private String description;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Type")
	@Enumerated(EnumType.ORDINAL)
	private Type type;

	@Basic(optional = false)
	@NotNull
	@Column(name = "System")
	private Boolean system;

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

	public MessageTemplate() {
	}

	public static enum Type {
		SMS,
		EMAIL;
	}

	public Integer getMessageTemplateId() {
		return messageTemplateId;
	}

	public void setMessageTemplateId(Integer messageTemplateId) {
		this.messageTemplateId = messageTemplateId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Boolean getSystem() {
		return system;
	}

	public Boolean isSystem() {
		return system;
	}

	public void setSystem(Boolean system) {
		this.system = system;
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
		return messageTemplateId;
	}
	@Override
	public void setId(Integer id) {
		this.messageTemplateId = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MessageTemplate)) {
			return false;
		}
		MessageTemplate that = (MessageTemplate) o;
		return getDeleted() == that.getDeleted() && Objects.equals(getMessageTemplateId(), that.getMessageTemplateId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getDescription(), that.getDescription()) && getType() == that.getType() && Objects.equals(getSystem(), that.getSystem()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMessageTemplateId(), getName(), getContent(), getDescription(), getType(), getSystem(), getDateCreated(), getDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MessageTemplate{");
		sb.append("messageTemplateId=").append(messageTemplateId);
		sb.append(", name='").append(name).append('\'');
		sb.append(", content='").append(content).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", type=").append(type);
		sb.append(", system=").append(system);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}
