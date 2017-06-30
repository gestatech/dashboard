package be.gestatech.ecosytem.entity.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.ecosytem.entity.Identifiable;
import be.gestatech.ecosytem.entity.delivery.DeliveryGroup;
import be.gestatech.ecosytem.entity.policy.Policy;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "USER_GROUP")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class UserGroups implements Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = -318814857920680370L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserGroupId")
	private Integer userGroupId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "Name")
	private String name;
	@Size(max = 200)
	@Column(name = "Description")
	private String description;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Deleted")
	private boolean deleted;

	@OrderColumn(name="user")
	@ManyToMany(mappedBy="userGroups", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Collection<Users> users;

	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;

	@OrderColumn(name="DeliveryGroup")
	@ManyToMany(mappedBy="userGroups")
	private Collection<DeliveryGroup> deliveryGroups;


	@OrderColumn(name="Policy")
	@ManyToMany(mappedBy="userGroups", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Collection<Policy> policies;

	public UserGroups() {
	}

	public UserGroups(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public UserGroups(Integer userGroupId, String name, Date dateCreated, boolean deleted) {
		this.userGroupId = userGroupId;
		this.name = name;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
	}

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userTypeId) {
		this.userGroupId = userTypeId;
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

	@Override
	public Integer getId() {
		return getUserGroupId();
	}

	@Override
	public void setId(Integer id) {
		setUserGroupId(id);
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

	@XmlTransient
	public Collection<Users> getUsers() {
		return users;
	}

	public void setUsers(Collection<Users> users) {
		this.users = users;
	}

	public Collection<DeliveryGroup> getDeliveryGroups() {
		return deliveryGroups;
	}

	public void setDeliveryGroups(Collection<DeliveryGroup> deliveryGroups) {
		this.deliveryGroups = deliveryGroups;
	}

	@Override
	public Users getUserCreated() {
		return userCreated;
	}
	@Override
	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	public Collection<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(Collection<Policy> policies) {
		this.policies = policies;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof UserGroups)) {
			return false;
		}
		UserGroups that = (UserGroups) o;
		return getDeleted() == that.getDeleted() && Objects.equals(getUserGroupId(), that.getUserGroupId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUsers(), that.getUsers()) && Objects.equals(getUserCreated(), that.getUserCreated()) && Objects.equals(getDeliveryGroups(), that.getDeliveryGroups()) && Objects.equals(getPolicies(), that.getPolicies());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserGroupId(), getName(), getDescription(), getDateCreated(), getDeleted(), getUsers(), getUserCreated(), getDeliveryGroups(), getPolicies());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserGroups{");
		sb.append("userGroupId=").append(userGroupId);
		sb.append(", name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", users=").append(users);
		sb.append(", userCreated=").append(userCreated);
		sb.append(", deliveryGroups=").append(deliveryGroups);
		sb.append(", policies=").append(policies);
		sb.append('}');
		return sb.toString();
	}
}
