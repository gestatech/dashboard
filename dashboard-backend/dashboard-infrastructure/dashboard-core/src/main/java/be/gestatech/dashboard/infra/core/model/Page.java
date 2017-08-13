package be.gestatech.dashboard.infra.core.model;

import java.util.List;

public class Page<T> {

    private List<T> content;

    private int count;

    public Page() {
        super();
    }

    public Page(final List<T> someContent, final int someCount) {
        this();
        content = someContent;
        count = someCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(final List<T> someContent) {
        content = someContent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int someCount) {
        count = someCount;
    }
}
