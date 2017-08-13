package be.gestatech.core.api.persistence;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by amurifa on 12/07/2017.
 */
@MappedSuperclass
public abstract class AbstractAuditable<E, PK extends Serializable> extends AbstractPersistable<PK> implements Auditable<E, PK, LocalDateTime> {

	@ManyToOne
	private E createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@ManyToOne
	private E lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Override
	public Optional<E> getCreatedBy() {
		return Optional.ofNullable(createdBy);
	}

	@Override
	public void setCreatedBy(E createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Optional<LocalDateTime> getCreatedDate() {
		return Objects.isNull(createdDate)? Optional.empty() : Optional.of(LocalDateTime.ofInstant(createdDate.toInstant(), ZoneId.systemDefault()));
	}

	@Override
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = Date.from(createdDate.atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public Optional<E> getLastModifiedBy() {
		return Optional.ofNullable(lastModifiedBy);
	}

	@Override
	public void setLastModifiedBy(E lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public Optional<LocalDateTime> getLastModifiedDate() {
		return Objects.isNull(lastModifiedDate) ? Optional.empty() : Optional.of(LocalDateTime.ofInstant(lastModifiedDate.toInstant(), ZoneId.systemDefault()));
	}

	@Override
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = Date.from(lastModifiedDate.atZone(ZoneId.systemDefault()).toInstant());
	}

}
