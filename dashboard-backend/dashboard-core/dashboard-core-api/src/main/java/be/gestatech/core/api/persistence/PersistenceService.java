package be.gestatech.core.api.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Created by amuri on 6/18/2017.
 */
public interface PersistenceService extends Serializable {

    String DEFAULT_UNIT_NAME = "default-persistence-unit";

    <T> void remove(T o);

    <T> void remove(Class<T> clazz, Long id);

    <T> T find(Class<T> clazz, Object id);

    <T extends Persistable> T createOrEdit(T entity);

    <T> List<T> executeQuery(QueryBuilder query);

    <T> T executeQuerySingle(QueryBuilder query);

    <T> Long getCount(Class<T> classForCount);

    <T> T edit(T entity);

    <T> T create(T entity);

    int executeUpdate(QueryBuilder queryBuilder);

    int executeNativeUpdate(String query);

    <T> List<T> executeNativeQuery(String query);

    <T> T executeNativeQuerySingle(String query);

    void flush();
}
