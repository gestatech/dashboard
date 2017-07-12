package be.gestatech.dashboard.core.jpa.entity.file;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.AbstractPersistable;
import be.gestatech.core.api.persistence.AuditEntityListener;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "FILE")
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "id", column = @Column(name = "FILE_ID"))
public class File extends AbstractPersistable<Long> implements Serializable {

	private static final long serialVersionUID = 1928870383056751178L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID")
	private Integer fileId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "FILE_NAME")
	private String fileName;

	@Basic(optional = false)
	@NotNull
	@Column(name = "EXTENSION")
	@Enumerated(EnumType.ORDINAL)
	private Extension extension;

	@Lob
	@NotNull
	@Column(name = "FILE", length = 1000000)
	private byte[] file;

	@Basic(optional = false)
	@NotNull
	@Column(name = "SIZE")
	private Long size;

	@Basic(optional = false)
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateCreated;

	@Basic(optional = false)
	@Column(name = "Deleted")
	private boolean deleted;
	@JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Users userCreated;

	public enum Extension {
		DOC, DOCX, XLS, XLSX, PDF, TXT, JPG, PNG, XML, SQL
	}

	public enum MimeType {
		DOC("application/msword"), DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"), XLS("application/vnd.ms-excel"), XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet "), PDF("application/pdf"), TXT("text/plain"), JPG("image/jpeg"), PNG("image/png"), XML("application/xml"), SQL("application/octet-stream");

		MimeType(String mimeType) {
			this.mimeType = mimeType;
		}

		private String mimeType;

		public String getMimeType() {
			return mimeType;
		}

		public void setMimeType(String mimeType) {
			this.mimeType = mimeType;
		}
	}

}
