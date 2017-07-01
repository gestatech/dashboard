package be.gestatech.dashboard.core.jpa.entity.message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.dashboard.core.jpa.entity.Identifiable;
import be.gestatech.dashboard.core.jpa.entity.delivery.DeliveryGroup;
import be.gestatech.dashboard.core.jpa.entity.user.Users;
import org.joda.time.DateTime;

import be.gestatech.dashboard.resources.Message;

/**
 * Entity class Describes Scheduler for sending messages
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "MessageScheduler")
@XmlRootElement
public class MessageScheduler implements Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 4717944326319985645L;

	@Id
	@Column(name = "MessageSchedulerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageSchedulerId;

	@JoinColumn(name = "MessageTemplate", referencedColumnName = "MessageTemplateId")
	@ManyToOne(optional = false)
	@NotNull
	private MessageTemplate messageTemplate;

	@ManyToMany
	@JoinTable(name = "MessageSchedulerHasDeliveryGroup", joinColumns = { @JoinColumn(name = "MessageSchedulerId", referencedColumnName = "MessageSchedulerId") }, inverseJoinColumns = { @JoinColumn(name = "DeliveryGroupId", referencedColumnName = "DeliveryGroupId") })
	private List<DeliveryGroup> deliveryGroups;

	@JoinColumn(name = "User", referencedColumnName = "UserId")
	@ManyToOne
	private Users user;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DateStart")
	@Temporal(TemporalType.DATE)
	private Date dateStart;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DateEnd")
	@Temporal(TemporalType.DATE)
	private Date dateEnd;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	@NotNull
	@Column(name = "DayOfWeekId", nullable = false)
	@ElementCollection(targetClass = DayOfWeek.class)
	@CollectionTable(name = "SchedulerHasDaysOfWeek", joinColumns = @JoinColumn(name = "SchedulerId"))
	@Enumerated(EnumType.ORDINAL)
	private List<DayOfWeek> daysOfWeek;

	@Basic(optional = false)
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

	public enum DayOfWeek implements Identifiable<Integer> {
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
		public void setId(Integer id) {

		}

		@Override
		public Users getUserCreated() {
			return null;
		}

		@Override
		public void setUserCreated(Users userCreated) {

		}

		@Override
		public Date getDateCreated() {
			return null;
		}

		@Override
		public void setDateCreated(Date datereated) {

		}

		@Override
		public boolean getDeleted() {
			return false;
		}

		@Override
		public void setDeleted(boolean deleted) {

		}
	}

	public Integer getMessageSchedulerId() {
		return messageSchedulerId;
	}

	public void setMessageSchedulerId(Integer messageSchedulerId) {
		this.messageSchedulerId = messageSchedulerId;
	}

	public List<DeliveryGroup> getDeliveryGroups() {
		return deliveryGroups;
	}

	public void setDeliveryGroups(List<DeliveryGroup> deliveryGroups) {
		this.deliveryGroups = deliveryGroups;
	}

	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = new DateTime(dateEnd).withTime(23, 59, 59, 999).toDate();
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
		return messageSchedulerId;
	}

	@Override
	public void setId(Integer id) {
		messageSchedulerId = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MessageScheduler)) {
			return false;
		}
		MessageScheduler that = (MessageScheduler) o;
		return getDeleted() == that.getDeleted() && Objects.equals(getMessageSchedulerId(), that.getMessageSchedulerId()) && Objects.equals(getMessageTemplate(), that.getMessageTemplate()) && Objects.equals(getDeliveryGroups(), that.getDeliveryGroups()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getDateStart(), that.getDateStart()) && Objects.equals(getDateEnd(), that.getDateEnd()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getDaysOfWeek(), that.getDaysOfWeek()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getUserCreated(), that.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMessageSchedulerId(), getMessageTemplate(), getDeliveryGroups(), getUser(), getDateStart(), getDateEnd(), getTime(), getDaysOfWeek(), getDateCreated(), getDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MessageScheduler{");
		sb.append("messageSchedulerId=").append(messageSchedulerId);
		sb.append(", messageTemplate=").append(messageTemplate);
		sb.append(", deliveryGroups=").append(deliveryGroups);
		sb.append(", user=").append(user);
		sb.append(", dateStart=").append(dateStart);
		sb.append(", dateEnd=").append(dateEnd);
		sb.append(", time=").append(time);
		sb.append(", daysOfWeek=").append(daysOfWeek);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}
