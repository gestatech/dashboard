package be.gestatech.dashboard.core.jpa.entity.delivery;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.dashboard.core.jpa.entity.message.MessageScheduler;
import be.gestatech.dashboard.core.jpa.entity.user.UserGroups;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes Users Group for message delivery
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = DeliveryGroup.TABLE_NAME)
@XmlRootElement
@AttributeOverride(name = "ID", column = @Column(name = "DELIVERY_GROUP_ID"))
public class DeliveryGroup extends AbstractPersistable<Long> implements Serializable {

	private static final long serialVersionUID = -9093609183394217742L;

	public static final String TABLE_NAME = "DELIVERY_GROUP";

	@Column(name = "DELIVERY_GROUP_ID")
	private Integer deliveryGroupId;

	@Basic(optional = false)
	@Column(name = "NAME")
	private String name;

	@Basic
	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "DELIVERY_GROUP_HAS_USERS", joinColumns = { @JoinColumn(name = "DELIVERY_GROUP", referencedColumnName = "DELIVERY_GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER", referencedColumnName = "USER_ID") })
	private List<Users> users;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "DELIVERY_GROUP_HAS_USER_GROUPS", joinColumns = { @JoinColumn(name = "DELIVERY_GROUP", referencedColumnName = "DELIVERY_GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_GROUP", referencedColumnName = "USER_GROUP_ID") })
	private List<UserGroups> userGroups;

	@ManyToMany(mappedBy = "DELIVERY_GROUPS")
	private List<MessageScheduler> messageSchedulers;

	@Basic(optional = false)
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateCreated;

	@Basic(optional = false)
	@Column(name = "DELETED")
	private boolean deleted;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Users userCreated;

	public DeliveryGroup() {
	}

	public Integer getDeliveryGroupId() {
		return deliveryGroupId;
	}

	public void setDeliveryGroupId(Integer deliveryGroupId) {
		this.deliveryGroupId = deliveryGroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public List<UserGroups> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroups> userGroups) {
		this.userGroups = userGroups;
	}

	public List<MessageScheduler> getMessageSchedulers() {
		return messageSchedulers;
	}

	public void setMessageSchedulers(List<MessageScheduler> messageSchedulers) {
		this.messageSchedulers = messageSchedulers;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
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
		if (!(o instanceof DeliveryGroup)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		DeliveryGroup that = (DeliveryGroup) o;
		return isDeleted() == that.isDeleted() && Objects.equals(getDeliveryGroupId(), that.getDeliveryGroupId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getUsers(), that.getUsers()) && Objects.equals(getUserGroups(), that.getUserGroups()) && Objects.equals(getMessageSchedulers(), that.getMessageSchedulers()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getDeliveryGroupId(), getName(), getDescription(), getUsers(), getUserGroups(), getMessageSchedulers(), getDateCreated(), isDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DeliveryGroup{");
		sb.append("deliveryGroupId=").append(deliveryGroupId);
		sb.append(", name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", users=").append(users);
		sb.append(", userGroups=").append(userGroups);
		sb.append(", messageSchedulers=").append(messageSchedulers);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}