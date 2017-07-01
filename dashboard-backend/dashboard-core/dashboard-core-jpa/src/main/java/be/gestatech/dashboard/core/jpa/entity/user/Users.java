package be.gestatech.dashboard.core.jpa.entity.user;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.dashboard.infra.util.IOUtils;
import be.gestatech.dashboard.infra.util.Utils;
import be.gestatech.dashboard.core.jpa.entity.address.Address;
import be.gestatech.dashboard.core.jpa.entity.Identifiable;
import be.gestatech.dashboard.core.jpa.entity.delivery.DeliveryGroup;
import be.gestatech.dashboard.core.jpa.entity.i18n.Locale;
import be.gestatech.dashboard.core.jpa.entity.method.Methods;
import be.gestatech.dashboard.core.jpa.entity.share.Share;

/**
 * Entity class Describes User
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "USER")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class Users implements Serializable,Identifiable<Integer> {

	private static final long serialVersionUID = -7010030343967468892L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	private Integer userId;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Foreigner")
	private Boolean foreigner;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "Username")
	private String username;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "Password")
	private String password;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "FirstName")
	private String firstName;
	@Size(max = 45)
	@Column(name = "MiddleName")
	private String middleName;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "LastName")
	private String lastName;
	@Column(name = "BirthDate")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	@Size(max = 45)
	@Column(name = "PhoneNumberHome")
	private String phoneNumberHome;
	@Size(max = 45)
	@Column(name = "PhoneNumberMobile")
	private String phoneNumberMobile;
	@Size(max = 45)
	@Column(name = "Email")
	private String email;
	@Size(max = 150)
	@Column(name = "Description")
	private String description;
	@Basic(optional = false)
	@NotNull
	@Column(name = "DateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Deleted")
	private boolean deleted;
	@Lob
	@Column(name = "AvatarImage")
	private byte[] avatarImage;
	@Basic(optional = false)
	@Size(max = 10)
	@Column(name = "Color", nullable=false, columnDefinition="varchar(150) default 'ffffff'")
	private String color;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "Address")
	private Address address;

	@ManyToOne
	@JoinColumn (name = "UserCreated")
	private Users userCreated;

	@ManyToOne
	@JoinColumn (name = "Locale")
	private Locale locale;

	@OrderColumn(name="UserGroup")
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name="UsersHasUserGroups",
			joinColumns={@JoinColumn(name="User", referencedColumnName="UserId")},
			inverseJoinColumns={@JoinColumn(name="UserGroup", referencedColumnName="UserGroupId")})
	private Collection<UserGroups> userGroups;

	/**
	 * If current user is Doctor he should have selected methods he can provide
	 */
	@OrderColumn(name="Method")
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name="DoctorsHasMethod",
			joinColumns={@JoinColumn(name="Doctor", referencedColumnName="UserId")},
			inverseJoinColumns={@JoinColumn(name="Method", referencedColumnName="MethodId")})
	private Collection<Methods> methods;

	@ManyToMany(mappedBy="users")
	@OrderColumn(name="deliveryGroup")
	private Collection<DeliveryGroup> deliveryGroups;

	@ManyToMany(mappedBy="doctors")
	@OrderColumn(name="shareCollectionDoctors")
	private Collection<Share> shareCollectionDoctors;

	@ManyToMany(mappedBy="assistants")
	@OrderColumn(name="shareCollectionAssistants")
	private Collection<Share> shareCollectionAssistants;

	@Basic(optional = false)
	@NotNull
	@Column(name = "MessagingAccepted")
	private boolean messagingAccepted;

	public Users() {
	}

	public Users(Integer userId) {
		this.userId = userId;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
		this.password = Utils.encrypt(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = Utils.firstUpperCase(firstName);
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = Utils.firstUpperCase(middleName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = Utils.firstUpperCase(lastName);
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
		this.phoneNumberHome = Utils.stripPhone(phoneNumberHome);
	}

	public String getPhoneNumberMobile() {
		return phoneNumberMobile;
	}

	public void setPhoneNumberMobile(String phoneNumberMobile) {
		this.phoneNumberMobile = Utils.stripPhone(phoneNumberMobile);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getForeigner() {
		return foreigner;
	}

	public Boolean isForeigner() {
		return foreigner;
	}

	public void setForeigner(Boolean isForeigner) {
		this.foreigner = isForeigner;
	}

	public Boolean IsForeigner() {
		return foreigner;
	}

	public Collection<DeliveryGroup> getDeliveryGroups() {
		return deliveryGroups;
	}

	public void setDeliveryGroups(Collection<DeliveryGroup> deliveryGroups) {
		this.deliveryGroups = deliveryGroups;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getMessagingAccepted() {
		return messagingAccepted;
	}

	public Boolean isMessagingAccepted() {
		return messagingAccepted;
	}

	public void setMessagingAccepted(Boolean messagingAccepted) {
		this.messagingAccepted = messagingAccepted;
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

	@XmlTransient
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

	@Override
	public Integer getId() {
		return getUserId();
	}
	@Override
	public void setId(Integer id) {
		setUserId(id);
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

	public byte[] getAvatarImage() {
		byte[] result = null;
		if (Objects.nonNull(avatarImage) && !Objects.equals(avatarImage.length, 0)) {
			result =  avatarImage;
		}else{
			final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			final String DEFAULT_AVATAR_IMAGE_PATH = "/resources/images/avatar/default_male_avatar.png";
			try {
				result =  IOUtils.toByteArray(new FileInputStream(new File(servletContext.getRealPath("") + File.separator + DEFAULT_AVATAR_IMAGE_PATH)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void setAvatarImage(byte[] avatarImage){
		if (Objects.nonNull(avatarImage) && !Objects.equals(avatarImage.length, 0)) {
			this.avatarImage = avatarImage;
		}else{
			final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			final String DEFAULT_AVATAR_IMAGE_PATH = "/resources/images/avatar/default_male_avatar.png";
			try {
				this.avatarImage =  IOUtils.toByteArray(new FileInputStream(new File(servletContext.getRealPath("") + File.separator + DEFAULT_AVATAR_IMAGE_PATH)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public StreamedContent getAvatarImageStream() throws IOException {
		return new DefaultStreamedContent(new ByteArrayInputStream(getAvatarImage()));
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
		return getDeleted() == users.getDeleted() && getMessagingAccepted() == users.getMessagingAccepted() && Objects.equals(getUserId(), users.getUserId()) && Objects.equals(getForeigner(), users.getForeigner()) && Objects.equals(getUsername(), users.getUsername()) && Objects.equals(getPassword(), users.getPassword()) && Objects.equals(getFirstName(), users.getFirstName()) && Objects.equals(getMiddleName(), users.getMiddleName()) && Objects.equals(getLastName(), users.getLastName()) && Objects.equals(getBirthDate(), users.getBirthDate()) && Objects.equals(getPhoneNumberHome(), users.getPhoneNumberHome()) && Objects.equals(getPhoneNumberMobile(), users.getPhoneNumberMobile()) && Objects.equals(getEmail(), users.getEmail()) && Objects.equals(getDescription(), users.getDescription()) && Objects.equals(getDateCreated(), users.getDateCreated()) && Arrays.equals(getAvatarImage(), users.getAvatarImage()) && Objects.equals(getColor(), users.getColor()) && Objects.equals(getAddress(), users.getAddress()) && Objects.equals(getUserCreated(), users.getUserCreated()) && Objects.equals(getLocale(), users.getLocale()) && Objects.equals(getUserGroups(), users.getUserGroups()) && Objects.equals(getMethods(), users.getMethods()) && Objects.equals(getDeliveryGroups(), users.getDeliveryGroups()) && Objects.equals(getShareCollectionDoctors(), users.getShareCollectionDoctors()) && Objects.equals(getShareCollectionAssistants(), users.getShareCollectionAssistants());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserId(), getForeigner(), getUsername(), getPassword(), getFirstName(), getMiddleName(), getLastName(), getBirthDate(), getPhoneNumberHome(), getPhoneNumberMobile(), getEmail(), getDescription(), getDateCreated(), getDeleted(), getAvatarImage(), getColor(), getAddress(), getUserCreated(), getLocale(), getUserGroups(), getMethods(), getDeliveryGroups(), getShareCollectionDoctors(), getShareCollectionAssistants(), getMessagingAccepted());
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
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", avatarImage=").append(Arrays.toString(avatarImage));
		sb.append(", color='").append(color).append('\'');
		sb.append(", address=").append(address);
		sb.append(", userCreated=").append(userCreated);
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