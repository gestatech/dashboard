package be.gestatech.dashboard.ejb.business.vo;

import java.util.Objects;

/**
 * Created by amurifa on 28/06/2017.
 */
public final class Translation {

	private String language;
	private String key;
	private String description;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Translation that = (Translation) o;
		return Objects.equals(language, that.language) && Objects.equals(key, that.key) && Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(language, key, description);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Translation{");
		sb.append("language='").append(language).append('\'');
		sb.append(", key='").append(key).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
