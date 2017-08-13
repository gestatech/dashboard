package be.gestatech.dashboard.web.rest;

import be.gestatech.dashboard.infra.cache.boundary.service.api.CacheService;
import be.gestatech.dashboard.infra.i18n.boundary.service.api.MessageBundleService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@Named(value = "msg")
public class MessageBunbleBean extends ResourceBundle {

    private static final Logger LOGGER = Logger.getLogger(MessageBunbleBean.class.getName());

    @Inject
    private MessageBundleService messageBundleService; //NOPMD cannot be made final, the value is injected

    @Inject
    private CacheService cacheService;

    public MessageBunbleBean() {
        super();
        messageBundleService = JSFUtils.getBean(MessageBundleService.BEAN_NAME, MessageBundleService.class);
        cacheService = JSFUtils.getBean(CacheService.BEAN_NAME, CacheService.class);
    }

    @Override
    protected Object handleGetObject(final String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        Cache cache = cacheService.getCache(CacheService.I18N_CACHE);
        MessageBundleKey bundleKey = new MessageBundleKey(key, locale.getLanguage());
        Element element = cache.get(bundleKey);
        String label;
        if (element == null || element.isExpired()) {
            LOGGER.debug("Getting message {} for language {} from DB", key, locale.getLanguage());
            label = messageBundleService.findByKeyAndLanguage(key, locale.getLanguage()).getLabel();
            element = new Element(bundleKey, label);
            cache.put(element);
        } else {
            LOGGER.debug("Getting message {} for language {} from CACHE", key, locale.getLanguage());
            label = (String) element.getObjectValue();
        }
        return label;
    }

    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(messageBundleService.findAllKey());
    }
}
