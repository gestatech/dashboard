package be.gestatech.dashboard.core.jdbc.dao.api;

import java.util.List;

/**
 * Created by amurifa on 28/06/2017.
 */
public interface DAOBaseValueObjectLocal<T> extends DAOBaseLocal<T> {

	T add(T valueObject) throws RuntimeException;

	List<T> add(List<T> valueObjects) throws RuntimeException;

	void update(T vo) throws RuntimeException;

	void update(List<T> valueObjects) throws RuntimeException;

	void remove(T valueObject) throws RuntimeException;

	void remove(List<T> valueObjects) throws RuntimeException;

	void removeByPrimaryKey(int primaryKey) throws RuntimeException;

	void removeByPrimaryKey(List<Integer> primaryKey) throws RuntimeException;

	void replaceInDatabase(List<T> newCollection, List<T> existingCollection) throws RuntimeException;
}
