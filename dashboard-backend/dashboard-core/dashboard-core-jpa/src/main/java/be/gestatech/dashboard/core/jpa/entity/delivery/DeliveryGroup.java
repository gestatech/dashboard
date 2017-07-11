package be.gestatech.dashboard.core.jpa.entity.delivery;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
@AttributeOverride(name = "id", column = @Column(name = "DELIVERY_GROUP_ID"))
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
		return Objects.equals(deliveryGroupId, that.deliveryGroupId) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(users, that.users) && Objects.equals(userGroups, that.userGroups) && Objects.equals(messageSchedulers, that.messageSchedulers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), deliveryGroupId, name, description, users, userGroups, messageSchedulers);
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
		sb.append('}');
		return sb.toString();
	}
}