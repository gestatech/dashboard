package be.gestatech.dashboard.web.jsf;

import javax.faces.application.FacesMessage;
import java.util.logging.Logger;

public class JSFUtil {

    private static final Logger LOGGER = Logger.getLogger(JSFUtil.class.getName());

    private JSFUtil() {
        throw new RuntimeException(String.format("Instantiation of {%s} is not allowed", JSFUtil.class.getName()));
    }

    private static void log(final String message, final FacesMessage.Severity severity, final Class beanClass) {
        if (FacesMessage.SEVERITY_ERROR.equals(severity) || FacesMessage.SEVERITY_FATAL.equals(severity)) {
            Logger.getLogger(beanClass.getName()).severe(message);
        } else if (FacesMessage.SEVERITY_INFO.equals(severity)) {
            Logger.getLogger(beanClass.getName()).info(message);
        } else if (FacesMessage.SEVERITY_WARN.equals(severity)) {
            Logger.getLogger(beanClass.getName()).warning(message);
        }
    }
}
