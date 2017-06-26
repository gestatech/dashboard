package be.gestatech.ecosytem.entity.client;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.core.api.persistence.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by amuri on 6/25/2017.
 */
@Entity
@EntityListeners(DateUpdateListener.class)
@Table(name = "CONTACT")
public class Contact implements Persistable, Serializable {

    private static final long serialVersionUID = -7192762386162216843L;

    @ManyToOne(optional = false)
    @JoinColumn(name = "companyId", referencedColumnName = "Id", nullable = false)
    private Company company;
    @OneToMany
    @ManyToOne(optional = false)
    @JoinColumn(name = "contact_typeId", referencedColumnName = "Id", nullable = false)
    private ContactType role;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, length = 19)
    private Long id;
    @Column(name = "Firstname", nullable = false, length = 60)
    private String firstname;
    @Column(name = "Lastname", nullable = false, length = 60)
    private String lastname;
    @Column(name = "Email", length = 100)
    private String email;
    @Column(name = "PhoneOffice", length = 20)
    private String phoneOffice;
    @Column(name = "PhoneMobile", length = 20)
    private String phoneMobile;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreateDate", nullable = false)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastUpdateDate", nullable = false)
    private Date updated;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ContactType getRole() {
        return role;
    }

    public void setRole(ContactType role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneOffice() {
        return phoneOffice;
    }

    public void setPhoneOffice(String phoneOffice) {
        this.phoneOffice = phoneOffice;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getCompany(), contact.getCompany()) &&
                Objects.equals(getRole(), contact.getRole()) &&
                Objects.equals(getId(), contact.getId()) &&
                Objects.equals(getFirstname(), contact.getFirstname()) &&
                Objects.equals(getLastname(), contact.getLastname()) &&
                Objects.equals(getEmail(), contact.getEmail()) &&
                Objects.equals(getPhoneOffice(), contact.getPhoneOffice()) &&
                Objects.equals(getPhoneMobile(), contact.getPhoneMobile()) &&
                Objects.equals(getCreated(), contact.getCreated()) &&
                Objects.equals(getUpdated(), contact.getUpdated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompany(), getRole(), getId(), getFirstname(), getLastname(), getEmail(), getPhoneOffice(), getPhoneMobile(), getCreated(), getUpdated());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contact{");
        sb.append("company=").append(company);
        sb.append(", role=").append(role);
        sb.append(", id=").append(id);
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneOffice='").append(phoneOffice).append('\'');
        sb.append(", phoneMobile='").append(phoneMobile).append('\'');
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append('}');
        return sb.toString();
    }
}
