package be.gestatech.dashboard.core.jpa.entity.delivery;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.base.BaseEntity;
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
//@SequenceGenerator(name = AbstractPersistable.GENERATOR, sequenceName="SQ_ADDRESS")
@AttributeOverride(name = "id", column = @Column(name = "DELIVERY_GROUP_ID"))
public class DeliveryGroup extends AbstractPersistable<Integer> implements Serializable {

	private static final long serialVersionUID = 2171221708916140889L;

	@Column(name = "DELIVERY_GROUP_ID")
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

}