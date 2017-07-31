package be.gestatech.dashboard.core.jpa.entity.address;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User's address
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Address.TABLE_NAME)
@XmlRootElement
@AttributeOverride(name = "ID", column = @Column(name = "ADDRESS_ID"))
public class Address extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -7396925612281759646L;

	public static final String TABLE_NAME = "ADDRESS";

	@Column(name = "ADDRESS")
	private Long addressId;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "REGION")
	private String region;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STREET")
	private String street;

	@Column(name = "POSTAL_CODE")
	private Integer postalCode;

	@Basic(optional = false)
	@XmlTransient
	@OneToMany(mappedBy = "ADDRESS", fetch = FetchType.LAZY)
	private Collection<Users> usersCollection;

	@Basic(optional = false)
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateCreated;

	@Basic(optional = false)
	@Column(name = "DELETED")
	private boolean deleted;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Users userCreated;

	public Address() {
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public Collection<Users> getUsersCollection() {
		return usersCollection;
	}

	public void setUsersCollection(Collection<Users> usersCollection) {
		this.usersCollection = usersCollection;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
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
		if (!(o instanceof Address)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Address address = (Address) o;
		return isDeleted() == address.isDeleted() && Objects.equals(getAddressId(), address.getAddressId()) && Objects.equals(getCountry(), address.getCountry()) && Objects.equals(getRegion(), address.getRegion()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getPostalCode(), address.getPostalCode()) && Objects.equals(getUsersCollection(), address.getUsersCollection()) && Objects.equals(getDateCreated(), address.getDateCreated()) && Objects.equals(getUserCreated(), address.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getAddressId(), getCountry(), getRegion(), getCity(), getStreet(), getPostalCode(), getUsersCollection(), getDateCreated(), isDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Address{");
		sb.append("addressId=").append(addressId);
		sb.append(", country='").append(country).append('\'');
		sb.append(", region='").append(region).append('\'');
		sb.append(", city='").append(city).append('\'');
		sb.append(", street='").append(street).append('\'');
		sb.append(", postalCode=").append(postalCode);
		sb.append(", usersCollection=").append(usersCollection);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}
