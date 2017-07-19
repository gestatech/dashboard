package be.gestatech.dashboard.core.jpa.entity.message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.core.api.persistence.Persistable;
import be.gestatech.dashboard.core.jpa.entity.delivery.DeliveryGroup;
import be.gestatech.dashboard.core.jpa.entity.user.Users;
import be.gestatech.dashboard.resources.Message;

/**
 * Entity class Describes Scheduler for sending messages
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = MessageScheduler.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "MESSAGE_SCHEDULER_ID"))
public class MessageScheduler extends AbstractPersistable<Long> implements Serializable {

	private static final long serialVersionUID = 4717944326319985645L;

	public static final String TABLE_NAME = "MESSAGE_SCHEDULER";

	@Column(name = "MESSAGE_SCHEDULER_ID")
	private Integer messageSchedulerId;

	@JoinColumn(name = "MESSAGE_TEMPLATE", referencedColumnName = "MESSAGE_TEMPLATE_ID")
	@ManyToOne(optional = false)
	@NotNull
	private MessageTemplate messageTemplate;

	@ManyToMany
	@JoinTable(name = "MESSAGE_SCHEDULER_HAS_DELIVERY_GROUP", joinColumns = { @JoinColumn(name = "MESSAGE_SCHEDULER_ID", referencedColumnName = "MESSAGE_SCHEDULER_ID") }, inverseJoinColumns = { @JoinColumn(name = "DELIVERY_GROUP_ID", referencedColumnName = "DELIVERY_GROUP_ID") })
	private List<DeliveryGroup> deliveryGroups;

	@JoinColumn(name = "USER", referencedColumnName = "USER_ID")
	@ManyToOne
	private Users user;

	@Basic(optional = false)
	@NotNull
	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	private Date satrtDate;

	@Basic(optional = false)
	@NotNull
	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Basic(optional = false)
	@NotNull
	@Column(name = "TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	@NotNull
	@Column(name = "DAY_OF_WEEK_ID", nullable = false)
	@ElementCollection(targetClass = DayOfWeek.class)
	@CollectionTable(name = "SCHEDULER_HAS_DAY_OF_WEEK", joinColumns = @JoinColumn(name = "SCHEDULER_ID"))
	@Enumerated(EnumType.ORDINAL)
	private List<DayOfWeek> daysOfWeek;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DELETED")
	private boolean deleted;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	public enum DayOfWeek implements Persistable<Integer> {

		MONDAY(Message.getMessage("CALENDAR_MONDAY")), TUESDAY(Message.getMessage("CALENDAR_TUESDAY")), WEDNESDAY(Message.getMessage("CALENDAR_WEDNESDAY")), THURSDAY(Message.getMessage("CALENDAR_THURSDAY")), FRIDAY(Message.getMessage("CALENDAR_FRIDAY")), SATURDAY(Message.getMessage("CALENDAR_SATURDAY")), SUNDAY(Message.getMessage("CALENDAR_SUNDAY"));

		DayOfWeek(String localName) {
			this.localName = localName;
		}

		String localName;

		public String getLocalName() {
			return localName;
		}

		public void setLocalName(String localName) {
			this.localName = localName;
		}

		@Override
		public Integer getId() {
			return ordinal();
		}

		@Override
		public boolean isNew() {
			return false;
		}
	}

	public Integer getMessageSchedulerId() {
		return messageSchedulerId;
	}

	public void setMessageSchedulerId(Integer messageSchedulerId) {
		this.messageSchedulerId = messageSchedulerId;
	}

	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public List<DeliveryGroup> getDeliveryGroups() {
		return deliveryGroups;
	}

	public void setDeliveryGroups(List<DeliveryGroup> deliveryGroups) {
		this.deliveryGroups = deliveryGroups;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getSatrtDate() {
		return satrtDate;
	}

	public void setSatrtDate(Date satrtDate) {
		this.satrtDate = satrtDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<DayOfWeek> getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(List<DayOfWeek> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MessageScheduler)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		MessageScheduler that = (MessageScheduler) o;
		return isDeleted() == that.isDeleted() && Objects.equals(getMessageSchedulerId(), that.getMessageSchedulerId()) && Objects.equals(getMessageTemplate(), that.getMessageTemplate()) && Objects.equals(getDeliveryGroups(), that.getDeliveryGroups()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getSatrtDate(), that.getSatrtDate()) && Objects.equals(getEndDate(), that.getEndDate()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getDaysOfWeek(), that.getDaysOfWeek()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getMessageSchedulerId(), getMessageTemplate(), getDeliveryGroups(), getUser(), getSatrtDate(), getEndDate(), getTime(), getDaysOfWeek(), getDateCreated(), isDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MessageScheduler{");
		sb.append("messageSchedulerId=").append(messageSchedulerId);
		sb.append(", messageTemplate=").append(messageTemplate);
		sb.append(", deliveryGroups=").append(deliveryGroups);
		sb.append(", user=").append(user);
		sb.append(", satrtDate=").append(satrtDate);
		sb.append(", endDate=").append(endDate);
		sb.append(", time=").append(time);
		sb.append(", daysOfWeek=").append(daysOfWeek);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}
