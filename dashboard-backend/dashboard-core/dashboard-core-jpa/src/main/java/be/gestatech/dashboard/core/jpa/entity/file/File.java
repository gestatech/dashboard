package be.gestatech.dashboard.core.jpa.entity.file;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import be.gestatech.core.api.persistence.DateUpdateListener;
import be.gestatech.dashboard.core.jpa.entity.Identifiable;
import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Created by amurifa on 30/06/2017.
 */
@Entity
@Table(name = "FILE")
@XmlRootElement
@EntityListeners(DateUpdateListener.class)
public class File implements Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 1928870383056751178L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FileRepositoryId")
	private Integer fileId;

	@Basic(optional = false)
	@NotNull
	@Column(name = "FileName")
	private String fileName;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Extension")
	@Enumerated(EnumType.ORDINAL)
	private Extension extension;

	@Lob
	@NotNull
	@Column(name = "File", length=1000000)
	private byte[] file;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Size")
	private Long size;

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

	public enum Extension {
		DOC,
		DOCX,
		XLS,
		XLSX,
		PDF,
		TXT,
		JPG,
		PNG,
		XML,
		SQL
	}

	public enum MimeType {
		DOC("application/msword"),
		DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
		XLS("application/vnd.ms-excel"),
		XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet "),
		PDF("application/pdf"),
		TXT("text/plain"),
		JPG("image/jpeg"),
		PNG("image/png"),
		XML("application/xml"),
		SQL("application/octet-stream");
		MimeType(String mimeType){
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

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileNameWithExtention() {
		return fileName + "." + extension.name().toLowerCase();
	}

	public String getMimeType(){
		return MimeType.valueOf(extension.name()).getMimeType();
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

	public boolean isDeleted() {
		return deleted;
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
		return getFileId();
	}

	@Override
	public void setId(Integer id) {
		setFileId(id);
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
		if (!(o instanceof File)) {
			return false;
		}
		File file1 = (File) o;
		return isDeleted() == file1.isDeleted() && Objects.equals(getFileId(), file1.getFileId()) && Objects.equals(getFileName(), file1.getFileName()) && getExtension() == file1.getExtension() && Arrays.equals(getFile(), file1.getFile()) && Objects.equals(getSize(), file1.getSize()) && Objects.equals(getDateCreated(), file1.getDateCreated()) && Objects.equals(getUserCreated(), file1.getUserCreated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFileId(), getFileName(), getExtension(), getFile(), getSize(), getDateCreated(), isDeleted(), getUserCreated());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("File{");
		sb.append("fileRepositoryId=").append(fileId);
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
