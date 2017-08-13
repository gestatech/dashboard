package be.gestatech.dashboard.core.jpa.entity.i18n;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Entity class Describes language Locale in the system
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = Locale.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "LOCAL_ID"))
public class Locale extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -3413937225651498117L;

	public static final String TABLE_NAME = "LOCALE";

	@Column(name = "LOCAL_ID")
	private Long localeId;

	@Column(name = "LANGUAGE_CODE")
	private String languageCode;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "LANGUAGE")
	private String language;

	@Column(name = "LANGUAGE_NATIVE")
	private String languageNative;

	@OneToMany(mappedBy = "LOCALE")
	private Collection<MessageBundle> messages;
	@OneToMany(mappedBy = "LOCALE")
	private Collection<Users> users;

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

	public Locale() {
	}

	public Long getLocaleId() {
		return localeId;
	}

	public void setLocaleId(Long localeId) {
		this.localeId = localeId;
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

	public Collection<MessageBundle> getMessages() {
		return messages;
	}

	public void setMessages(Collection<MessageBundle> messages) {
		this.messages = messages;
	}

	public Collection<Users> getUsers() {
		return users;
	}

	public void setUsers(Collection<Users> users) {
		this.users = users;
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
		if (!(o instanceof Locale)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Locale locale = (Locale) o;
		return isDeleted() == locale.isDeleted() && Objects.equals(getLocaleId(), locale.getLocaleId()) && Objects.equals(getLanguageCode(), locale.getLanguageCode()) && Objects.equals(getCountryCode(), locale.getCountryCode()) && Objects.equals(getLanguage(), locale.getLanguage()) && Objects.equals(getLanguageNative(), locale.getLanguageNative()) && Objects.equals(getMessages(), locale.getMessages()) && Objects.equals(getUsers(), locale.getUsers()) && Objects.equals(getDateCreated(), locale.getDateCreated()) && Objects.equals(getUserCreated(), locale.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getLocaleId(), getLanguageCode(), getCountryCode(), getLanguage(), getLanguageNative(), getMessages(), getUsers(), getDateCreated(), isDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Locale{");
		sb.append("localeId=").append(localeId);
		sb.append(", languageCode='").append(languageCode).append('\'');
		sb.append(", countryCode='").append(countryCode).append('\'');
		sb.append(", language='").append(language).append('\'');
		sb.append(", languageNative='").append(languageNative).append('\'');
		sb.append(", messages=").append(messages);
		sb.append(", users=").append(users);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}
