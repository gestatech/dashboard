package be.gestatech.dashboard.resources;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by amurifa on 30/06/2017.
 */
public class Configuration extends LocalizedResource {

	private static final String BUNDLE_NAME = "configuration";

	private static final Logger LOG = Logger.getLogger(Configuration.class.getName());
	// We do not have locale for config constants so we have only one instance of
	// ResourceBundle and can use it for all locales
	private static Configuration instance = null;
	public Configuration(){
		super();
		instance = this;
	}

	@Override
	String getBundleName() {
		return BUNDLE_NAME;
	}

	public static ResourceBundle getInstance() {
		if (Objects.isNull(instance)) {
			return new Configuration();
		} else{
			return instance;
		}
	}

	public static void clear(){
		LOG.info("Message Bundle reload called");
		instance = new Configuration();
	}

	public static String getMessage(String key) {
		if(getInstance() == null) return "";
		return getInstance().getString(key);
	}
}