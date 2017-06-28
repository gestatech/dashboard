package be.gestatech.dashboard.core.jdbc.dao.api;

import java.util.List;

/**
 * Created by amurifa on 28/06/2017.
 */
public interface DAOReferenceDataLocal<T> extends DAOBaseLocal<T> {

	List<T> getAll() throws RuntimeException;
}
