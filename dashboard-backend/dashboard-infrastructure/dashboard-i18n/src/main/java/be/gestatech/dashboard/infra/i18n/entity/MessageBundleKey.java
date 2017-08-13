package be.gestatech.dashboard.infra.i18n.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MessageBundleKey implements Serializable {

    private static final long serialVersionUID = -5157045022420438166L;

    @Column(name = "BUNDLE_KEY")
    private String key;

    @Column(name = "LANGUAGE")
    private String language;

    public MessageBundleKey() {
    }

    public MessageBundleKey(final String someKey, final String someLanguage) {
        key = someKey;
        language = someLanguage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String someKey) {
        key = someKey;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String someLanguage) {
        language = someLanguage;
    }

    @Override
    public boolean equals(Object other) {
        boolean isEqual = false;
        if (Objects.equals(this, other)) {
            isEqual = true;
        } else if (!(other instanceof MessageBundleKey)) {
            isEqual = false;
        } else {
            MessageBundleKey that = (MessageBundleKey) other;
            isEqual = Objects.equals(getKey(), that.getKey()) && Objects.equals(getLanguage(), that.getLanguage());
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getLanguage());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageBundleKey{");
        sb.append("key='").append(key).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
