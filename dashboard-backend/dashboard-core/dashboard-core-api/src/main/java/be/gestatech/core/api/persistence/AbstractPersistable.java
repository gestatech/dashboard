package be.gestatech.core.api.persistence;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Created by amurifa on 7/07/2017.
 * <p>
 * Abstract base class for entities. Allows parameterization of id type, chooses auto-generation and implements
 * {@link #equals(Object)} and {@link #hashCode()} based on that id.
 *
 * @param <PK>
 * 		the type of the identifier.
 */
@MappedSuperclass
public abstract class AbstractPersistable<PK extends Serializable> implements Persistable<PK> {

	//public static final String GENERATOR = "customSequence";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.TABLE)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	private PK id;

	@Column(name = "VERSION")
	@Version
	private Long version;

	public AbstractPersistable() {
	}

	public PK getId() {
		return id;
	}

	protected void setId(final PK id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Transient
	public boolean isNew() {
		return Objects.isNull(getId());
	}

	@Override
	public boolean equals(Object other) {
		if (Objects.equals(this, other)) {
			return true;
		}
		if (!(other instanceof AbstractPersistable)) {
			return false;
		}
		AbstractPersistable<?> that = (AbstractPersistable<?>) other;
		return Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AbstractPersistable{");
		sb.append("id=").append(id);
		sb.append('}');
		return sb.toString();
	}
}
