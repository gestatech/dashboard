package be.gestatech.dashboard.infra.audit.infra.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

/**
 * Created by amurifa on 12/07/2017.
 */
@ApplicationScoped
public class AuditConfig {

	private final static Logger LOGGER = Logger.getLogger(AuditConfig.class.getName());

	/**
	 * Default user name
	 */
	public static String DEFAULT_USER = "Unknown";

	/**
	 * Maximal possible characters to use as a value in {@link AuditChange}
	 */
	public static int MAX_CHARACTERS = 4000;

	/**
	 * Default pattern for date values in {@link AuditChange}
	 */
	public static String TIMESTAMP_PATTERN = "dd.MM.yyyy HH:mm:ss";

	/**
	 * ZoneId Object time zone for LocalDateTime instance creation.
	 */
	public static String LOCAL_DATE_TIME_ZONE = "UTC";

	/**
	 * Name of the (optional) audit property file
	 */
	private static final String auditPropertiesFilename = "gestatech.audit.properties";

	static Properties properties = new Properties();

	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
		AuditConfig.loadProperties();
	}

	private static void loadProperties() {
		InputStream inputStream = null;
		try {
			inputStream = AuditConfig.class.getClassLoader().getResourceAsStream(auditPropertiesFilename);
			if (Objects.nonNull(inputStream)) {
				properties.load(inputStream);
				LOGGER.info(() -> String.format("Reading {%s}",auditPropertiesFilename));
				DEFAULT_USER = loadProperty(DEFAULT_USER, "gestatech.audit.default.user");
				MAX_CHARACTERS = loadProperty(MAX_CHARACTERS, "gestatech.audit.max.character");
				LOCAL_DATE_TIME_ZONE = loadProperty(LOCAL_DATE_TIME_ZONE, "gestatech.audit.timezone");
				TIMESTAMP_PATTERN = loadProperty(TIMESTAMP_PATTERN, "gestatech.audit.timestamp.pattern");
			}
		} catch (IOException e) {
			LOGGER.info(() -> String.format("Couldn't closed {%s} >>>> {%s}", new Object[] { auditPropertiesFilename, e}));
		} finally {
			try {
				if (Objects.nonNull(inputStream)) {
					inputStream.close();
				}
			} catch (IOException e) {
				LOGGER.warning(() -> String.format("Couldn't closed {%s} >>>> {%s}", new Object[] { auditPropertiesFilename, e}));
			}
		}
	}

	private static String loadProperty(String value, String key) {
		String property = properties.getProperty(key);
		if (Objects.isNull(property)) {
			return value;
		}
		LOGGER.info(() -> String.format("Audit Property {%s} loaded: {%s}", new Object[]{ key, property }));
		return property;
	}

	private static int loadProperty(int value, String key) {
		String property = properties.getProperty(key);
		if (Objects.nonNull(property)) {
			try {
				LOGGER.info(() -> String.format("Audit Property {%s} loaded: {%s}", new Object[]{ key, property }));
				return Integer.valueOf(property).intValue();
			} catch (NumberFormatException e) {
				LOGGER.info(() -> String.format("Audit Property {%s} value invalid: {%s}", new Object[]{ key, property }));
			}
		}
		return value;
	}

}
