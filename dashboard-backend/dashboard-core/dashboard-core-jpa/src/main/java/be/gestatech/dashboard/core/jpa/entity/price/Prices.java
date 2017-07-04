package be.gestatech.dashboard.core.jpa.entity.price;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.dashboard.core.jpa.entity.BaseEntity;
import be.gestatech.dashboard.core.jpa.entity.method.Methods;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "PRICE")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Prices.findAll", query = "SELECT p FROM Prices p"),
	@NamedQuery(name = "Prices.findByPriceId", query = "SELECT p FROM Prices p WHERE p.priceId = :priceId"),
	@NamedQuery(name = "Prices.findByTotal", query = "SELECT p FROM Prices p WHERE p.total = :total"),
	@NamedQuery(name = "Prices.findByDateTime", query = "SELECT p FROM Prices p WHERE p.dateTime = :dateTime"),
	@NamedQuery(name = "Prices.findByDateCreated", query = "SELECT p FROM Prices p WHERE p.dateCreated = :dateCreated"),
	@NamedQuery(name = "Prices.findByDeleted", query = "SELECT p FROM Prices p WHERE p.deleted = :deleted")
})
@EntityListeners(DateUpdateListener.class)
public class Prices extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 5457462759625938435L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "PriceId")
	private Integer priceId;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Total")
	private float total;
	@Column(name = "DateTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Deleted")
	private boolean deleted;
	@JoinColumn(name = "Method", referencedColumnName = "MethodId")
	@ManyToOne(optional = false)
	private Methods method;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false)
	private Users userCreated;

	public Prices() {
	}

	public Prices(Integer priceId) {
		this.priceId = priceId;
	}

	public Prices(Integer priceId, float total, Date dateCreated, boolean deleted) {
		this.priceId = priceId;
		this.total = total;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
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

	public Methods getMethod() {
		return method;
	}

	public void setMethod(Methods method) {
		this.method = method;
	}

	@Override
	public Integer getId() {
		return getPriceId();
	}

	@Override
	public void setId(Integer id) {
		setPriceId(id);
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Prices)) {
			return false;
		}
		Prices prices = (Prices) o;
		return Float.compare(prices.getTotal(), getTotal()) == 0 && getDeleted() == prices.getDeleted() && Objects.equals(getPriceId(), prices.getPriceId()) && Objects.equals(getDateTime(), prices.getDateTime()) && Objects.equals(getDateCreated(), prices.getDateCreated()) && Objects.equals(getMethod(), prices.getMethod()) && Objects.equals(getUserCreated(), prices.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPriceId(), getTotal(), getDateTime(), getDateCreated(), getDeleted(), getMethod(), getUserCreated());
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
