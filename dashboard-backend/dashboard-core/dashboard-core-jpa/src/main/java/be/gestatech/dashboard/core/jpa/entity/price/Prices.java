package be.gestatech.dashboard.core.jpa.entity.price;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.method.Methods;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Prices.TABLE_NAME)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Prices.findAll", query = "SELECT p FROM Prices p"),
	@NamedQuery(name = "Prices.findByPriceId", query = "SELECT p FROM Prices p WHERE p.priceId = :priceId"),
	@NamedQuery(name = "Prices.findByTotal", query = "SELECT p FROM Prices p WHERE p.total = :total"),
	@NamedQuery(name = "Prices.findByDateTime", query = "SELECT p FROM Prices p WHERE p.dateTime = :dateTime"),
	@NamedQuery(name = "Prices.findByDateCreated", query = "SELECT p FROM Prices p WHERE p.dateCreated = :dateCreated"),
	@NamedQuery(name = "Prices.findByDeleted", query = "SELECT p FROM Prices p WHERE p.deleted = :deleted")
})
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "PRICE_ID"))
public class Prices extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 5457462759625938435L;

	public static final String TABLE_NAME = "PRICE";

	@Column(name = "PRICE_ID")
	private Long priceId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "TOTAL")
	private float total;

	@Column(name = "DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Basic(optional = false)
	@Column(name = "DELETED")
	private boolean deleted;

	@JoinColumn(name = "METHOD", referencedColumnName = "METHOD_ID")
	@ManyToOne(optional = false)
	private Methods method;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Users userCreated;

	public Prices() {
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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

	public Methods getMethod() {
		return method;
	}

	public void setMethod(Methods method) {
		this.method = method;
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
		if (!(o instanceof Prices)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Prices prices = (Prices) o;
		return Float.compare(prices.getTotal(), getTotal()) == 0 && isDeleted() == prices.isDeleted() && Objects.equals(getPriceId(), prices.getPriceId()) && Objects.equals(getDateTime(), prices.getDateTime()) && Objects.equals(getDateCreated(), prices.getDateCreated()) && Objects.equals(getMethod(), prices.getMethod()) && Objects.equals(getUserCreated(), prices.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getPriceId(), getTotal(), getDateTime(), getDateCreated(), isDeleted(), getMethod(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Prices{");
		sb.append("priceId=").append(priceId);
		sb.append(", total=").append(total);
		sb.append(", dateTime=").append(dateTime);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", method=").append(method);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}
