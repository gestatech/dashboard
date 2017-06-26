package be.gestatech.ecosytem.entity.client;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.core.api.persistence.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by amuri on 6/25/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Company.COMPANY_ENTITY + ".findAll", query = "select c from Company c order by c.name"),
        @NamedQuery(name = Company.COMPANY_ENTITY + ".findAllByName", query = "select c from Company c WHERE c.name LIKE :clientName ORDER BY c.name")})
@Table(name = "COMPANY")
@EntityListeners(DateUpdateListener.class)
public class Company implements Persistable, Serializable, Comparable<Company> {

    private static final long serialVersionUID = -1318476674710548595L;

    public static final String COMPANY_ENTITY = "Company";

    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL})
    @JoinColumn(name = "companyId", referencedColumnName = "Id", nullable = false)
    private List<Contact> contacts;
    @OneToMany
    @JoinColumn(name = "companyId", referencedColumnName = "Id", nullable = false)
    private List<Document> documents;
    @OneToMany
    @JoinColumn(name = "companyId", referencedColumnName = "Id", nullable = false)
    private List<ClientNote> notes;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, length = 19)
    private Long id;
    @Column(name = "Name", nullable = false, length = 100)
    private String name;
    @Column(name = "Street", length = 100)
    private String street;
    @Column(name = "ZipCode", length = 10)
    private String zipCode;
    @Column(name = "City", length = 60)
    private String city;
    @Column(name = "Email", length = 100)
    private String email;
    @Column(name = "Phone", length = 20)
    private String phone;
    @Column(name = "Fax", length = 20)
    private String fax;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Created", nullable = false)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Updated", nullable = false)
    private Date updated;
    @Column(name = "Logo", length = 100)
    private String logo;
    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL})
    @JoinColumn(name = "companyId", referencedColumnName = "Id", nullable = false)
    private List<Project> projects;
    @Column(name = "Website", length = 100)
    private String website;
    @Column(name = "PostalBox", length = 50)
    private String postalBox;

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

    public int compareTo(Company o) {
        if(Objects.isNull(this.getName())) {
            return -1;
        }
        return this.getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getId() == company.getId() &&
                Objects.equals(contacts, company.contacts) &&
                Objects.equals(documents, company.documents) &&
                Objects.equals(notes, company.notes) &&
                Objects.equals(name, company.name) &&
                Objects.equals(street, company.street) &&
                Objects.equals(zipCode, company.zipCode) &&
                Objects.equals(city, company.city) &&
                Objects.equals(email, company.email) &&
                Objects.equals(phone, company.phone) &&
                Objects.equals(fax, company.fax) &&
                Objects.equals(getCreated(), company.getCreated()) &&
                Objects.equals(getUpdated(), company.getUpdated()) &&
                Objects.equals(logo, company.logo) &&
                Objects.equals(projects, company.projects) &&
                Objects.equals(website, company.website) &&
                Objects.equals(postalBox, company.postalBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contacts, documents, notes, getId(), name, street, zipCode, city, email, phone, fax, getCreated(), getUpdated(), logo, projects, website, postalBox);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("contacts=").append(contacts);
        sb.append(", documents=").append(documents);
        sb.append(", notes=").append(notes);
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", fax='").append(fax).append('\'');
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", logo='").append(logo).append('\'');
        sb.append(", projects=").append(projects);
        sb.append(", website='").append(website).append('\'');
        sb.append(", postalBox='").append(postalBox).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
