package be.gestatech.dashboard.core.jpa.entity.address;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
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
@AttributeOverride(name = "id", column = @Column(name = "ADDRESS_ID"))
public class Address extends AbstractPersistable<Long> implements Serializable {

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
		return Objects.equals(addressId, address.addressId) && Objects.equals(country, address.country) && Objects.equals(region, address.region) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(postalCode, address.postalCode) && Objects.equals(usersCollection, address.usersCollection);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), addressId, country, region, city, street, postalCode, usersCollection);
	}
}
