package be.gestatech.dashboard.core.jdbc.dao.api;

import javax.ejb.ObjectNotFoundException;

/**
 * Created by amurifa on 28/06/2017.
 */
public interface DAOBaseLocal<T> {

	T getByPK(int id) throws ObjectNotFoundException;

	T getByPKForUpdate(int id) throws ObjectNotFoundException;
}
