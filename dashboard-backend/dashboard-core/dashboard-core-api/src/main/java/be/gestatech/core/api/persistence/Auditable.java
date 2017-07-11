/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.core.api.persistence;


import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * @author gestatech
 *
 * Interface for auditable entities. Allows storing and retrieving creation and modification information. The changing
 * instance (typically some user) is to be defined by a generics definition.
 *
 * @param <E> the auditing type. Typically some kind of user.
 * @param <ID> the type of the audited type's identifier
 */
public interface Auditable<E ,ID, T extends TemporalAccessor> extends Persistable<ID> {

	Optional<E> getCreatedBy();

	void setCreatedBy(E createdBy);

	Optional<T> getCreatedDate();

	void setCreatedOn(T creationDate);

	Optional<E> getLastModifiedBy();

	void setLastModifiedBy(E lastModifiedBy);

	Optional<T> getLastModifiedDate();

	void setLastModifiedDate(T lastModifiedDate);

}