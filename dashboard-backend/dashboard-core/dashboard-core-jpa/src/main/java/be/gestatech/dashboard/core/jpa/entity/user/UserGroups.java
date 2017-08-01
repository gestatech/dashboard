package be.gestatech.dashboard.core.jpa.entity.user;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.delivery.DeliveryGroup;
import be.gestatech.dashboard.core.jpa.entity.policy.Policy;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = UserGroups.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "USER_GROUP_ID"))
public class UserGroups extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -318814857920680370L;

	public static final String TABLE_NAME = "USER_GROUP";

	@Column(name = "USER_GROUP_ID")
	private Long userGroupId;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "NAME")
	private String name;

	@Size(max = 200)
	@Column(name = "DESCRIPTION")
	private String description;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DELETED")
	private boolean deleted;

	@OrderColumn(name="USER")
	@ManyToMany(mappedBy="USER_GROUPS", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Collection<Users> users;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	@OrderColumn(name="DELIVERY_GROUP")
	@ManyToMany(mappedBy="USER_GROUPS")
	private Collection<DeliveryGroup> deliveryGroups;


	@OrderColumn(name="POLICY")
	@ManyToMany(mappedBy="USER_GROUPS", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Collection<Policy> policies;

	public UserGroups() {
	}

	public Long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
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

	public Collection<Users> getUsers() {
		return users;
	}

	public void setUsers(Collection<Users> users) {
		this.users = users;
	}

	public Users getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	public Collection<DeliveryGroup> getDeliveryGroups() {
		return deliveryGroups;
	}

	public void setDeliveryGroups(Collection<DeliveryGroup> deliveryGroups) {
		this.deliveryGroups = deliveryGroups;
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
		if (!super.equals(o)) {
			return false;
		}
		UserGroups that = (UserGroups) o;
		return isDeleted() == that.isDeleted() && Objects.equals(getUserGroupId(), that.getUserGroupId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUsers(), that.getUsers()) && Objects.equals(getUserCreated(), that.getUserCreated()) && Objects.equals(getDeliveryGroups(), that.getDeliveryGroups()) && Objects.equals(getPolicies(), that.getPolicies());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getUserGroupId(), getName(), getDescription(), getDateCreated(), isDeleted(), getUsers(), getUserCreated(), getDeliveryGroups(), getPolicies());
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
