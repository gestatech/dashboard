package be.gestatech.core.api.persistence;

import be.gestatech.dashboard.infra.audit.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by amuri on 6/18/2017.
 */
public class QueryBuilder {

    private static final Logger logger = Logger.getLogger(QueryBuilder.class.getName());

    public static final String INNER_JOIN = " INNER JOIN ";

    public static final String OUTER_JOIN = " LEFT OUTER JOIN ";

    public enum Condition {
        AND, OR
    }

    private long firstRow = -1;

    private long count = -1;

    private boolean cacheable = true;

    private StringBuilder query = new StringBuilder();

    private StringBuilder filter = new StringBuilder();

    private StringBuilder orderBy = new StringBuilder();

    private StringBuilder groupBy = new StringBuilder();

    private Map<String, Object> parameters = new HashMap<>();

    public QueryBuilder(String sql) {
        query.append(sql);
    }

    public void addParameter(String name, String parameterName, Object value) {
        addParameter(name, parameterName, value, Condition.AND, false, false);
    }

    public void addParameter(String name, String parameterName, Object value, boolean isLike) {
        addParameter(name, parameterName, value, Condition.AND, isLike, isLike);
    }

    public void addParameter(String name, String parameterName, Object value, boolean isLike, boolean isUpper) {
        addParameter(name, parameterName, value, Condition.AND, isLike, isUpper);
    }

    public void addParameter(String name, String parameterName, Object value, Condition condition, boolean isLike, boolean isUpper) {
        // do not support null values yet
        if (isEmptyValue(value)) {
            return;
        }
        registerParameter(parameterName, value, isLike, isUpper);
        if (filter.length() != 0) {
            filter.append(' ').append(condition.name()).append(' ');
        }
        filter.append('(');
        if (isUpper) {
            filter.append("UPPER(");
        }
        filter.append(name);
        if (isUpper) {
            filter.append(')');
        }
        if (isLike) {
            filter.append(" LIKE ");
        }
        else {
            filter.append('=');
        }
        if (isUpper) {
            filter.append("UPPER(");
        }
        filter.append(':');
        filter.append(parameterName);
        if (isUpper) {
            filter.append(')');
        }
        filter.append(')');
    }

    private void registerParameter(String parameterName, Object value, boolean isLike, boolean isUpper) {
        if (value instanceof String) {
            StringBuilder parsedValue = new StringBuilder(((String) value).trim());
            if (isLike) {
                parsedValue.insert(0, '%');
                parsedValue.append('%');
            }
            parameters.put(parameterName, parsedValue.toString());
        }
        else {
            parameters.put(parameterName, value);
        }
    }

    private boolean isEmptyValue(Object value) {
        if (value instanceof String) {
            return StringUtils.isEmpty((String) value);
        }
        return value == null;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public String getQuery() {
        StringBuilder fullQuery = new StringBuilder();
        fullQuery.append(query);
        if (filter.length() != 0) {
            fullQuery.append(" WHERE ");
            fullQuery.append(filter.toString());
        }
        if (groupBy.length() != 0) {
            fullQuery.append(' ');
            fullQuery.append(groupBy);
            fullQuery.append(' ');
        }
        if (orderBy.length() != 0) {
            fullQuery.append(' ');
            fullQuery.append(orderBy);
            fullQuery.append(' ');
        }
        String result = fullQuery.toString();
        if (logger.isLoggable(Level.INFO)) {
            logger.info(result);
            if (!parameters.isEmpty()) {
                StringBuilder params = new StringBuilder();
                for (Map.Entry<String, Object> o : parameters.entrySet()) {
                    if (params.length() > 0) {
                        params.append(',');
                    }
                    params.append(o.getKey());
                    params.append('=');
                    params.append(o.getValue());
                }
                logger.info(String.format("Parameters: [%s]",params));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return getQuery();
    }

    public void addOrderBy(String parameters) {
        orderBy.append(" ORDER BY ");
        orderBy.append(parameters);
    }

    public void addJoin(String joinType, String joinQuery) {
        if (query.indexOf(joinQuery) < 0) {
            query.append(joinType);
            query.append(joinQuery);
        }
    }

    public void addExpression(String expression, String parameterName, Object value) {
        addExpression(expression, parameterName, Condition.AND, value);
    }

    public void addExpression(String expression) {
        registerExpression(expression, Condition.AND);
    }

    public void addExpression(String expression, Condition condition) {
        registerExpression(expression, condition);
    }

    public void addExpression(String expression, String parameterName, Condition condition, Object value) {
        // do not support null values yet
        if (isEmptyValue(value)) {
            return;
        }
        registerParameter(parameterName, value, false, false);
        registerExpression(expression, condition);
    }

    private void registerExpression(String expression, Condition condition) {
        if (filter.length() != 0) {
            filter.append(' ').append(condition.name()).append(' ');
        }
        filter.append('(');
        filter.append(expression);
        filter.append(')');
    }

    public boolean isEmpty() {
        return filter.length() < 1 && parameters.isEmpty();
    }

    public void addExpression(String expression, String[] parameterNames, Object[] values) {
        if (values.length < 0) {
            return;
        }
        int i = 0;
        for (String parameterName : parameterNames) {
            registerParameter(parameterName, values[i++], false, false);
        }
        registerExpression(expression, Condition.AND);
    }

    public long getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(long firstRow) {
        this.firstRow = firstRow;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public void addGroup(String parameters) {
        orderBy.append(" GROUP BY ");
        orderBy.append(parameters);
    }
}
