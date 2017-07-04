package be.gestatech.dashboard.core.jpa.entity.address;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.dashboard.core.jpa.entity.BaseEntity;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes User's address
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "ADDRESS")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class Address extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = -7396925612281759646L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AddressId")
	private Integer addressId;
	@Column(name = "Country")
	private String country;
	@Column(name = "Region")
	private String region;
	@Column(name = "City")
	private String city;
	@Column(name = "Address")
	private String address;
	@Column(name = "PostIndex")
	private Integer postIndex;
	@Basic(optional = false)
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Basic(optional = false)
	@Column(name = "Deleted")
	private boolean deleted;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false,fetch= FetchType.LAZY)
	private Users userCreated;
	@OneToMany(mappedBy = "address",fetch= FetchType.LAZY)
	private Collection<Users> usersCollection;

	public Address() {
	}

	public Address(Integer addressId) {
		this.addressId = addressId;
	}

	public Address(Integer addressId, Date dateCreated, boolean deleted) {
		this.addressId = addressId;
		this.dateCreated = dateCreated;
		this.deleted = deleted;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPostIndex() {
		return postIndex;
	}

	public void setPostIndex(Integer postIndex) {
		this.postIndex = postIndex;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean getDeleted() {
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

	@XmlTransient
	public Collection<Users> getUsersCollection() {
		return usersCollection;
	}

	public void setUsersCollection(Collection<Users> usersCollection) {
		this.usersCollection = usersCollection;
	}
	@Override
	public Integer getId() {
		return getAddressId();
	}

	@Override
	public void setId(Integer id) {
		setAddressId(id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Address)) {
			return false;
		}
		Address address1 = (Address) o;
		return getDeleted() == address1.getDeleted() && Objects.equals(getAddressId(), address1.getAddressId()) && Objects.equals(getCountry(), address1.getCountry()) && Objects.equals(getRegion(), address1.getRegion()) && Objects.equals(getCity(), address1.getCity()) && Objects.equals(getAddress(), address1.getAddress()) && Objects.equals(getPostIndex(), address1.getPostIndex()) && Objects.equals(getDateCreated(), address1.getDateCreated()) && Objects.equals(getUserCreated(), address1.getUserCreated()) && Objects.equals(getUsersCollection(), address1.getUsersCollection());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAddressId(), getCountry(), getRegion(), getCity(), getAddress(), getPostIndex(), getDateCreated(), getDeleted(), getUserCreated(), getUsersCollection());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Address{");
		sb.append("addressId=").append(addressId);
		sb.append(", country='").append(country).append('\'');
		sb.append(", region='").append(region).append('\'');
		sb.append(", city='").append(city).append('\'');
		sb.append(", address='").append(address).append('\'');
		sb.append(", postIndex=").append(postIndex);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append(", usersCollection=").append(usersCollection);
		sb.append('}');
		return sb.toString();
	}
}
