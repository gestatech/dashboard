package be.gestatech.dashboard.infra.core.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PageRequest {

    private int start;

    private int pageSize;

    private SortOrder sortOrder;

    private String sortField;

    private Map<String, String> filters = new HashMap<String, String>();

    public PageRequest() {
        super();
    }

    public PageRequest(final int someStart, final int somePageSize, final SortOrder someSortOrder,
                       final String someSortField, final Map<String, String> someFilters) {
        this();
        start = someStart;
        pageSize = somePageSize;
        sortOrder = someSortOrder;
        sortField = someSortField;
        filters = someFilters;
    }

    public int getStart() {
        return start;
    }

    public void setStart(final int someStart) {
        start = someStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(final int somePageSize) {
        pageSize = somePageSize;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(final SortOrder someSortOrder) {
        sortOrder = someSortOrder;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(final String someSortField) {
        sortField = someSortField;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void addFilter(final String filter, final String value) {
        filters.put(filter,  value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageRequest)) return false;
        PageRequest that = (PageRequest) o;
        return getStart() == that.getStart() &&
                getPageSize() == that.getPageSize() &&
                Objects.equals(getSortOrder(), that.getSortOrder()) &&
                Objects.equals(getSortField(), that.getSortField()) &&
                Objects.equals(getFilters(), that.getFilters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getPageSize(), getSortOrder(), getSortField(), getFilters());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageRequest{");
        sb.append("start=").append(start);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", sortField='").append(sortField).append('\'');
        sb.append(", filters=").append(filters);
        sb.append('}');
        return sb.toString();
    }
}

