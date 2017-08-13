package be.gestatech.dashboard.core.jpa.entity.policy;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.user.UserGroups;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Policy.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "POLICY_ID"))
public class Policy extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -5320164799443804945L;

	public static final String TABLE_NAME = "POLICY";

	@Column(name = "POLICY_ID")
	private Long policyId;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "STRING_ID")
	private String stringId;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "POLICY_GROUP")
	private String policyGroup;

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

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	@OrderColumn(name="USER_GROUP")
	@ManyToMany(fetch= FetchType.LAZY, cascade = { CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name="POLICY_HAS_USER_GROUPS",
			joinColumns={@JoinColumn(name="POLICY", referencedColumnName="POLICY_ID")},
			inverseJoinColumns={@JoinColumn(name="USER_GROUP", referencedColumnName="USER_GROUP_ID")})
	private Collection<UserGroups> userGroups;

	public Policy() {
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getPolicyGroup() {
		return policyGroup;
	}

	public void setPolicyGroup(String policyGroup) {
		this.policyGroup = policyGroup;
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

	public Users getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Users userCreated) {
		this.userCreated = userCreated;
	}

	public Collection<UserGroups> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Collection<UserGroups> userGroups) {
		this.userGroups = userGroups;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Policy)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Policy policy = (Policy) o;
		return isDeleted() == policy.isDeleted() && Objects.equals(getPolicyId(), policy.getPolicyId()) && Objects.equals(getStringId(), policy.getStringId()) && Objects.equals(getPolicyGroup(), policy.getPolicyGroup()) && Objects.equals(getDescription(), policy.getDescription()) && Objects.equals(getDateCreated(), policy.getDateCreated()) && Objects.equals(getUserCreated(), policy.getUserCreated()) && Objects.equals(getUserGroups(), policy.getUserGroups());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getPolicyId(), getStringId(), getPolicyGroup(), getDescription(), getDateCreated(), isDeleted(), getUserCreated(), getUserGroups());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Policy{");
		sb.append("policyId=").append(policyId);
		sb.append(", stringId='").append(stringId).append('\'');
		sb.append(", policyGroup='").append(policyGroup).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append(", userGroups=").append(userGroups);
		sb.append('}');
		return sb.toString();
	}
}
