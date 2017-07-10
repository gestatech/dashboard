package be.gestatech.dashboard.core.jpa.entity.address;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@AttributeOverride(name = "id", column = @Column(name = "addressId"))
public class Address extends AbstractPersistable<Integer> implements Serializable {

	private static final long serialVersionUID = -7396925612281759646L;

	public static final String TABLE_NAME = "ADDRESS";

	private Integer addressId;

	private String country;

	private String region;

	private String city;

	private String street;

	private Integer postalCode;

	@Basic(optional = false)
	@XmlTransient
	@OneToMany(mappedBy = TABLE_NAME, fetch = FetchType.LAZY)
	private Collection<Users> usersCollection;

	public Address() {
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
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
		return Objects.equals(getAddressId(), address.getAddressId()) && Objects.equals(getCountry(), address.getCountry()) && Objects.equals(getRegion(), address.getRegion()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getPostalCode(), address.getPostalCode()) && Objects.equals(getUsersCollection(), address.getUsersCollection());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getAddressId(), getCountry(), getRegion(), getCity(), getStreet(), getPostalCode(), getUsersCollection());
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
		sb.append('}');
		return sb.toString();
	}
}
