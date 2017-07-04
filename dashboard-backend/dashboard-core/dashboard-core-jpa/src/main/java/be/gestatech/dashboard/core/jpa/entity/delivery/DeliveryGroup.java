package be.gestatech.dashboard.core.jpa.entity.delivery;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.dashboard.core.jpa.entity.BaseEntity;
import be.gestatech.dashboard.core.jpa.entity.user.Users;
import be.gestatech.dashboard.core.jpa.entity.message.MessageScheduler;
import be.gestatech.dashboard.core.jpa.entity.user.UserGroups;

/**
 * Entity class Describes Users Group for message delivery
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "DELIVERY_GROUP")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class DeliveryGroup extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 2171221708916140889L;

	@Id
	@Column(name = "DeliveryGroupId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deliveryGroupId;

	@Basic(optional = false)
	@Column(name = "Name")
	private String name;

	@Basic
	@Column(name = "Description")
	private String description;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "DeliveryGroupHasUsers",
			joinColumns = {@JoinColumn(name = "DeliveryGroup", referencedColumnName = "DeliveryGroupId")},
			inverseJoinColumns = {@JoinColumn(name = "User", referencedColumnName = "UserId")})
	private List<Users> users;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "DeliveryGroupHasUserGroups",
			joinColumns = {@JoinColumn(name = "DeliveryGroup", referencedColumnName = "DeliveryGroupId")},
			inverseJoinColumns = {@JoinColumn(name = "UserGroup", referencedColumnName = "UserGroupId")})
	private List<UserGroups> userGroups;

	@ManyToMany(mappedBy="deliveryGroups")
	private List<MessageScheduler> messageSchedulers;

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
		return deliveryGroupId;
	}
	@Override
	public void setId(Integer id) {
		this.deliveryGroupId = id;
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
		DeliveryGroup that = (DeliveryGroup) o;
		return getDeleted() == that.getDeleted() && Objects.equals(getDeliveryGroupId(), that.getDeliveryGroupId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getUsers(), that.getUsers()) && Objects.equals(getUserGroups(), that.getUserGroups()) && Objects.equals(getMessageSchedulers(), that.getMessageSchedulers()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDeliveryGroupId(), getName(), getDescription(), getUsers(), getUserGroups(), getMessageSchedulers(), getDateCreated(), getDeleted(), getUserCreated());
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