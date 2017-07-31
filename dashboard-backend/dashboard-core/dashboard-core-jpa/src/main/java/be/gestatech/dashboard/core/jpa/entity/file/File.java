package be.gestatech.dashboard.core.jpa.entity.file;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

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
@Table(name = File.TABLE_NAME)
@XmlRootElement
@EntityListeners(AuditEntityListener.class)
@AttributeOverride(name = "ID", column = @Column(name = "FILE_ID"))
public class File extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1928870383056751178L;

	public static final String TABLE_NAME = "FILE";

	@Column(name = "FILE_ID")
	private Long fileId;

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
	@Column(name = "DELETED")
	private boolean deleted;

	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Users userCreated;

	public File() {
	}

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

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Extension getExtension() {
		return extension;
	}

	public void setExtension(Extension extension) {
		this.extension = extension;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
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
		if (!(o instanceof File)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		File file1 = (File) o;
		return isDeleted() == file1.isDeleted() && Objects.equals(getFileId(), file1.getFileId()) && Objects.equals(getFileName(), file1.getFileName()) && getExtension() == file1.getExtension() && Arrays.equals(getFile(), file1.getFile()) && Objects.equals(getSize(), file1.getSize()) && Objects.equals(getDateCreated(), file1.getDateCreated()) && Objects.equals(getUserCreated(), file1.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getFileId(), getFileName(), getExtension(), getFile(), getSize(), getDateCreated(), isDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("File{");
		sb.append("fileId=").append(fileId);
		sb.append(", fileName='").append(fileName).append('\'');
		sb.append(", extension=").append(extension);
		sb.append(", file=").append(Arrays.toString(file));
		sb.append(", size=").append(size);
		sb.append(", dateCreated=").append(dateCreated);
		sb.append(", deleted=").append(deleted);
		sb.append(", userCreated=").append(userCreated);
		sb.append('}');
		return sb.toString();
	}
}
