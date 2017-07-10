package be.gestatech.core.api.persistence;

/**
 * Created by amurifa on 7/07/2017.
 *
 * @param <ID> the type of the identifier
 */
public interface Persistable<ID> {

	/**
	 * Returns the id of the entity.
	 *
	 * @return the id
	 */
	ID getId();

	/**
	 * Returns if the {@code Persistable} is new or was persisted already.
	 *
	 * @return if the object is new
	 */
	boolean isNew();
}