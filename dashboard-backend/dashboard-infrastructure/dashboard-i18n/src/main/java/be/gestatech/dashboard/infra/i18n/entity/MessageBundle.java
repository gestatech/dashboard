package be.gestatech.dashboard.infra.i18n.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MESSAGE_BUNDLE")
public class MessageBundle implements Serializable {

    private static final long serialVersionUID = -4589871290630549578L;

    public static final String KEY_FIELD = "key";
    public static final String LANGUAGE_FIELD = "language";
    public static final String VALUE_FIELD = "label";

    @Id
    private MessageBundleKey id;

    @Column(name = "LABEL")
    private String label;

    public MessageBundleKey getId() {
        return id;
    }

    public void setId(final MessageBundleKey someId) {
        id = someId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(final String someLabel) {
        label = someLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageBundle)) return false;
        MessageBundle that = (MessageBundle) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getLabel(), that.getLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLabel());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageBundle{");
        sb.append("id=").append(id);
        sb.append(", label='").append(label).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
