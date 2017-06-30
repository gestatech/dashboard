package be.gestatech.ecosytem.entity.i18n;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.ecosytem.entity.Identifiable;
import be.gestatech.ecosytem.entity.user.Users;

/**
 * Entity class Describes language Locale in the system
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "LOCALE")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class Locale implements Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = -3413937225651498117L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LocaleId")
	private Integer localeId;
	@Column(name = "LanguageCode")
	private String languageCode;
	@Column(name = "CountryCode")
	private String countryCode;
	@Column(name = "Language")
	private String language;
	@Column(name = "LanguageNative")
	private String languageNative;
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
	@OneToMany(mappedBy = "locale")
	private Collection<MessageBundle> messages;
	@OneToMany(mappedBy = "locale")
	private Collection<Users> users;

	public Locale() {
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguageNative() {
		return languageNative;
	}

	public void setLanguageNative(String languageNative) {
		this.languageNative = languageNative;
	}

	public Integer getLocaleId() {
		return localeId;
	}

	public void setLocaleId(Integer localeId) {
		this.localeId = localeId;
	}

	public Collection<Users> getUsers() {
		return users;
	}

	public void setUsers(Collection<Users> users) {
		this.users = users;
	}

	@Override
	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isDeleted() {
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
		return getLocaleId();
	}

	@Override
	public void setId(Integer id) {
		setLocaleId(id);
	}

	@Override
	public boolean getDeleted() {
		return deleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Locale)) {
			return false;
		}
		Locale locale = (Locale) o;
		return getDeleted() == locale.getDeleted() && Objects.equals(localeId, locale.localeId) && Objects.equals(languageCode, locale.languageCode) && Objects.equals(countryCode, locale.countryCode) && Objects.equals(language, locale.language) && Objects.equals(languageNative, locale.languageNative) && Objects.equals(getDateCreated(), locale.getDateCreated()) && Objects.equals(getUserCreated(), locale.getUserCreated()) && Objects.equals(messages, locale.messages) && Objects.equals(users, locale.users);
	}

	@Override
	public int hashCode() {
		return Objects.hash(localeId, languageCode, countryCode, language, languageNative, getDateCreated(), getDeleted(), getUserCreated(), messages, users);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Locale{");
		sb.append("localeId=").append(localeId);
		sb.append(", languageCode='").append(languageCode).append('\'');
		sb.append(", countryCode='").append(countryCode).append('\'');
		sb.append(", language='").append(language).append('\'');
		sb.append(", languageNative='").append(languageNative).append('\'');
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append(", messages=").append(messages);
		sb.append(", user=").append(users);
		sb.append('}');
		return sb.toString();
	}
}
