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

import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.infra.util.IOUtils;
import be.gestatech.dashboard.infra.util.Utils;
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
@Table(name = "USER")
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
public class Users implements Serializable {

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
}
