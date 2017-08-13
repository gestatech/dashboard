package be.gestatech.dashboard.core.jpa.entity.user;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.address.Address;
import be.gestatech.dashboard.core.jpa.entity.delivery.DeliveryGroup;
import be.gestatech.dashboard.core.jpa.entity.i18n.Locale;
import be.gestatech.dashboard.core.jpa.entity.method.Methods;
import be.gestatech.dashboard.core.jpa.entity.share.Share;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Users.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "USER_ID"))
public class Users implements Serializable {

	private static final long serialVersionUID = -7010030343967468892L;

	public static final String TABLE_NAME = "USER";

	@Column(name = "USER_ID")
	private Long userId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "FOREIGNER")
	private Boolean foreigner;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "USERNAME")
	private String username;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "PASSWORD")
	private String password;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "FISRTNAME")
	private String firstName;

	@Size(max = 45)
	@Column(name = "MIDDLENAME")
	private String middleName;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "LASTNAME")
	private String lastName;

	@Column(name = "BIRTHDATE")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Size(max = 45)
	@Column(name = "PHONE_NUMBER_HOME")
	private String phoneNumberHome;

	@Size(max = 45)
	@Column(name = "PHONE_NUMBER_MOBILE")
	private String phoneNumberMobile;

	@Size(max = 45)
	@Column(name = "EMAIL")
	private String email;

	@Size(max = 150)
	@Column(name = "DESCRIPTION")
	private String description;

	@Lob
	@Column(name = "AVATAR_IMAGE")
	private byte[] avatarImage;

	@Basic(optional = false)
	@Size(max = 10)
	@Column(name = "COLOR", nullable=false, columnDefinition="varchar(150) default 'ffffff'")
	private String color;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "ADDRESS")
	private Address address;

	@ManyToOne
	@JoinColumn (name = "LOCALE")
	private Locale locale;

	@OrderColumn(name="USER_GROUP")
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name="USERS_HAS_USER_GROUPS",
			joinColumns={@JoinColumn(name="USER", referencedColumnName="USER_ID")},
			inverseJoinColumns={@JoinColumn(name="USER_GROUP", referencedColumnName="USER_GROUP_ID")})
	private Collection<UserGroups> userGroups;

	/**
	 * If current user is Doctor he should have selected methods he can provide
	 */
	@OrderColumn(name="METHOD")
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name="DOCTORS_HAS_METHOD",
			joinColumns={@JoinColumn(name="DOCTOR", referencedColumnName="USER_ID")},
			inverseJoinColumns={@JoinColumn(name="METHOD", referencedColumnName="METHOD_ID")})
	private Collection<Methods> methods;

	@ManyToMany(mappedBy="USERS")
	@OrderColumn(name="DELIVERY_GROUP")
	private Collection<DeliveryGroup> deliveryGroups;

	@ManyToMany(mappedBy="DOCTORS")
	@OrderColumn(name="shareCollectionDoctors")
	private Collection<Share> shareCollectionDoctors;

	@ManyToMany(mappedBy="ASSISTANTS")
	@OrderColumn(name="SHARE_COLLECTION_ASSISTANTS")
	private Collection<Share> shareCollectionAssistants;

	@Basic(optional = false)
	@NotNull
	@Column(name = "MESSAGING_ACCEPTED")
	private boolean messagingAccepted;

	public Users() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getForeigner() {
		return foreigner;
	}

	public void setForeigner(Boolean foreigner) {
		this.foreigner = foreigner;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumberHome() {
		return phoneNumberHome;
	}

	public void setPhoneNumberHome(String phoneNumberHome) {
		this.phoneNumberHome = phoneNumberHome;
	}

	public String getPhoneNumberMobile() {
		return phoneNumberMobile;
	}

	public void setPhoneNumberMobile(String phoneNumberMobile) {
		this.phoneNumberMobile = phoneNumberMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(byte[] avatarImage) {
		this.avatarImage = avatarImage;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Collection<UserGroups> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Collection<UserGroups> userGroups) {
		this.userGroups = userGroups;
	}

	public Collection<Methods> getMethods() {
		return methods;
	}

	public void setMethods(Collection<Methods> methods) {
		this.methods = methods;
	}

	public Collection<DeliveryGroup> getDeliveryGroups() {
		return deliveryGroups;
	}

	public void setDeliveryGroups(Collection<DeliveryGroup> deliveryGroups) {
		this.deliveryGroups = deliveryGroups;
	}

	public Collection<Share> getShareCollectionDoctors() {
		return shareCollectionDoctors;
	}

	public void setShareCollectionDoctors(Collection<Share> shareCollectionDoctors) {
		this.shareCollectionDoctors = shareCollectionDoctors;
	}

	public Collection<Share> getShareCollectionAssistants() {
		return shareCollectionAssistants;
	}

	public void setShareCollectionAssistants(Collection<Share> shareCollectionAssistants) {
		this.shareCollectionAssistants = shareCollectionAssistants;
	}

	public boolean isMessagingAccepted() {
		return messagingAccepted;
	}

	public void setMessagingAccepted(boolean messagingAccepted) {
		this.messagingAccepted = messagingAccepted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Users)) {
			return false;
		}
		Users users = (Users) o;
		return isMessagingAccepted() == users.isMessagingAccepted() && Objects.equals(getUserId(), users.getUserId()) && Objects.equals(getForeigner(), users.getForeigner()) && Objects.equals(getUsername(), users.getUsername()) && Objects.equals(getPassword(), users.getPassword()) && Objects.equals(getFirstName(), users.getFirstName()) && Objects.equals(getMiddleName(), users.getMiddleName()) && Objects.equals(getLastName(), users.getLastName()) && Objects.equals(getBirthDate(), users.getBirthDate()) && Objects.equals(getPhoneNumberHome(), users.getPhoneNumberHome()) && Objects.equals(getPhoneNumberMobile(), users.getPhoneNumberMobile()) && Objects.equals(getEmail(), users.getEmail()) && Objects.equals(getDescription(), users.getDescription()) && Arrays.equals(getAvatarImage(), users.getAvatarImage()) && Objects.equals(getColor(), users.getColor()) && Objects.equals(getAddress(), users.getAddress()) && Objects.equals(getLocale(), users.getLocale()) && Objects.equals(getUserGroups(), users.getUserGroups()) && Objects.equals(getMethods(), users.getMethods()) && Objects.equals(getDeliveryGroups(), users.getDeliveryGroups()) && Objects.equals(getShareCollectionDoctors(), users.getShareCollectionDoctors()) && Objects.equals(getShareCollectionAssistants(), users.getShareCollectionAssistants());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserId(), getForeigner(), getUsername(), getPassword(), getFirstName(), getMiddleName(), getLastName(), getBirthDate(), getPhoneNumberHome(), getPhoneNumberMobile(), getEmail(), getDescription(), getAvatarImage(), getColor(), getAddress(), getLocale(), getUserGroups(), getMethods(), getDeliveryGroups(), getShareCollectionDoctors(), getShareCollectionAssistants(), isMessagingAccepted());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Users{");
		sb.append("userId=").append(userId);
		sb.append(", foreigner=").append(foreigner);
		sb.append(", username='").append(username).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", middleName='").append(middleName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", birthDate=").append(birthDate);
		sb.append(", phoneNumberHome='").append(phoneNumberHome).append('\'');
		sb.append(", phoneNumberMobile='").append(phoneNumberMobile).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", avatarImage=").append(Arrays.toString(avatarImage));
		sb.append(", color='").append(color).append('\'');
		sb.append(", address=").append(address);
		sb.append(", locale=").append(locale);
		sb.append(", userGroups=").append(userGroups);
		sb.append(", methods=").append(methods);
		sb.append(", deliveryGroups=").append(deliveryGroups);
		sb.append(", shareCollectionDoctors=").append(shareCollectionDoctors);
		sb.append(", shareCollectionAssistants=").append(shareCollectionAssistants);
		sb.append(", messagingAccepted=").append(messagingAccepted);
		sb.append('}');
		return sb.toString();
	}
}
