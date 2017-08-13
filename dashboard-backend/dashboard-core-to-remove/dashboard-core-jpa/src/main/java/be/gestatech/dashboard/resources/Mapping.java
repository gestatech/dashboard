package be.gestatech.dashboard.resources;

import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by amurifa on 30/06/2017.
 */
public class Mapping extends LocalizedResource {

	private static final String BUNDLE_NAME = "mapping";

	// We do not have locale for mapping strings so we have only one instance of
	// ResourceBundle and can use it for all locales
	private static Mapping instance;

	public Mapping() {
		super();
		instance = this;
	}

	@Override
	String getBundleName() {
		return BUNDLE_NAME;
	}

	public static ResourceBundle getInstance() {
		ResourceBundle resourceBundle = instance;
		if (Objects.isNull(instance)) {
			resourceBundle = new Mapping();
		}
		return resourceBundle;
	}

	/**
	 * Method for retrieving paths
	 *
	 * @param key
	 * 		to search for
	 *
	 * @return message correspondent to given key
	 */
	public static String getMessage(String key) {
		String message = getInstance().getString(key);
		if (Objects.isNull(getInstance())) {
			return "";
		}
		return message;
	}
}
