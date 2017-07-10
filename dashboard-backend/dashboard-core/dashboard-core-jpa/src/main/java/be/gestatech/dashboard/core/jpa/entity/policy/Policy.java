package be.gestatech.dashboard.core.jpa.entity.policy;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.base.BaseEntity;
import be.gestatech.dashboard.core.jpa.entity.user.UserGroups;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "POLICY")
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
public class Policy extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = -5320164799443804945L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PolicyId")
	private Integer policyId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "StringId")
	private String stringId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "PolicyGroup")
	private String policyGroup;
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
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;

	@OrderColumn(name="UserGroup")
	@ManyToMany(fetch= FetchType.LAZY, cascade = { CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name="PolicyHasUserGroups",
			joinColumns={@JoinColumn(name="Policy", referencedColumnName="PolicyId")},
			inverseJoinColumns={@JoinColumn(name="UserGroup", referencedColumnName="UserGroupId")})
	private Collection<UserGroups> userGroups;

	public Policy() {
	}

	public Policy(Integer policyId) {
		this.policyId = policyId;
	}

	public Policy(Integer policyId, String name, Date dateCreated, boolean deleted) {
		this.policyId = policyId;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPolicyGroup() {
		return policyGroup;
	}

	public void setPolicyGroup(String policyGroup) {
		this.policyGroup = policyGroup;
	}

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	@XmlTransient
	public Collection<UserGroups> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Collection<UserGroups> userGroups) {
		this.userGroups = userGroups;
	}

	@Override
	public Integer getId() {
		return getPolicyId();
	}

	@Override
	public void setId(Integer id) {
		setPolicyId(id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Policy)) {
			return false;
		}
		Policy policy = (Policy) o;
		return getDeleted() == policy.getDeleted() && Objects.equals(getPolicyId(), policy.getPolicyId()) && Objects.equals(getStringId(), policy.getStringId()) && Objects.equals(getPolicyGroup(), policy.getPolicyGroup()) && Objects.equals(getDescription(), policy.getDescription()) && Objects.equals(getDateCreated(), policy.getDateCreated()) && Objects.equals(getUserCreated(), policy.getUserCreated()) && Objects.equals(getUserGroups(), policy.getUserGroups());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPolicyId(), getStringId(), getPolicyGroup(), getDescription(), getDateCreated(), getDeleted(), getUserCreated(), getUserGroups());
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
