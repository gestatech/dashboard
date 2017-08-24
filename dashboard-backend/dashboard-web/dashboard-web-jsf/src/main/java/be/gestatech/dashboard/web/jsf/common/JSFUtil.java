package be.gestatech.dashboard.web.jsf.common;

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import java.util.List;
import java.util.logging.Logger;

public class JSFUtil {

    private static final Logger LOGGER = Logger.getLogger(JSFUtil.class.getName());

    private JSFUtil() {
        throw new RuntimeException(String.format("Instantiation of {%s} is not allowed", JSFUtil.class.getName()));
    }

    public static <T> T getBean(final String beanName, final FacesContext facesContext) {
        final Object returnObject = facesContext.getELContext().getELResolver().getValue(facesContext.getELContext(), null, beanName);
        if (returnObject == null) {
            throw new RuntimeException("Couldn't get bean named: " +  beanName);
        }
        return (T) returnObject;
    }

    public static <T> T getBean(final String beanName, final Class<T> cls) {
        if (beanName.charAt(0) == '#') {
            throw new IllegalArgumentException("Method expects the bean name, not an EL expression");
        }
        StringBuffer expression = new StringBuffer();
        expression.append("#{").append(beanName).append('}');
        return evaluateExpression(expression.toString(), cls);
    }

    public static <T> T evaluateExpression(final String valueExpression, final Class<T> cls) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ValueExpression expression = facesContext.getApplication().getExpressionFactory().createValueExpression(facesContext.getELContext(), valueExpression,
                cls);
        return (T) expression.getValue(facesContext.getELContext());
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

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JSFUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
}
