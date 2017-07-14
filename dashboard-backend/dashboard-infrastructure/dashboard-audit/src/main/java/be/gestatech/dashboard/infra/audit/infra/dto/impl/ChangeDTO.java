package be.gestatech.dashboard.infra.audit.infra.dto.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import be.gestatech.dashboard.infra.audit.infra.config.AuditConfig;

/**
 * Created by amurifa on 13/07/2017.
 */
public class ChangeDTO {
	private Object oldValue;
	private Object newValue;
	private String field;

	public ChangeDTO(Object oldValue, Map.Entry<String, Object> newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue.getValue();
		this.field = newValue.getKey();
	}

	public String getField() {
		return field;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public Object getNewValue() {
		return newValue;
	}

	public String getOldValueString() {
		return toString(oldValue);
	}

	public String getNewValueString() {
		return toString(newValue);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ChangeDTO)) {
			return false;
		}
		ChangeDTO changeDTO = (ChangeDTO) o;
		return Objects.equals(oldValue, changeDTO.oldValue) && Objects.equals(newValue, changeDTO.newValue) && Objects.equals(field, changeDTO.field);
	}

	@Override
	public int hashCode() {
		return Objects.hash(oldValue, newValue, field);
	}

	@Override
	public String toString() {
		return field + ": " + toString(oldValue) + " -> " + toString(newValue);
	}

	private String toString(Object object) {
		if (object == null) {
			return null;
		}
		if (object instanceof LocalDateTime) {
			return ((LocalDateTime) object).format(DateTimeFormatter.ofPattern(AuditConfig.TIMESTAMP_PATTERN));
		}
		if (object instanceof Date) {
			return new SimpleDateFormat(AuditConfig.TIMESTAMP_PATTERN).format(((Date) object));
		}

		String value = object.toString();

		if (value.length() > AuditConfig.MAX_CHARACTERS) {
			value = value.substring(0, AuditConfig.MAX_CHARACTERS - 5) + " ...";
		}
		return value;
	}
}
