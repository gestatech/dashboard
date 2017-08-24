package be.gestatech.dashboard.web.jsf.i18n;

import be.gestatech.dashboard.infra.cache.boundary.facade.api.CacheFacade;
import be.gestatech.dashboard.infra.i18n.boundary.service.api.MessageBundleService;
import be.gestatech.dashboard.infra.i18n.entity.MessageBundleKey;
import be.gestatech.dashboard.web.jsf.common.JSFUtil;
import be.gestatech.dashboard.web.jsf.producer.FacesContextProducer;
import be.gestatech.dashboard.web.jsf.qualifier.ProjectStageProvider;

import javax.cache.Cache;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.logging.Logger;

@Named(value = "msg")
public class MessageBunbleBean extends ResourceBundle {

    private static final Logger LOGGER = Logger.getLogger(MessageBunbleBean.class.getName());

    @Inject
    private MessageBundleService messageBundleService; //NOPMD cannot be made final, the value is injected

    @Inject
    private CacheFacade cacheFacade;

    @Inject
    @ProjectStageProvider
    private FacesContextProducer facesContextProducer;

    public MessageBunbleBean() {
        super();
        messageBundleService = JSFUtil.getBean(MessageBundleService.BEAN_NAME, MessageBundleService.class);
        cacheFacade = JSFUtil.getBean(CacheFacade.BEAN_NAME, CacheFacade.class);
    }

    @Override
    protected Object handleGetObject(final String key) {

        FacesContext context = facesContextProducer.getFacesContext();
        Locale locale = context.getViewRoot().getLocale();

        Cache cache = cacheFacade.getCache(CacheFacade.I18N_CACHE);

        MessageBundleKey bundleKey = new MessageBundleKey(key, locale.getLanguage());
        Object o = cache.get(bundleKey);
        String label = null;
        if (Objects.nonNull(element) || element.isExpired()) {
            LOGGER.debug("Getting message {} for language {} from DB", key, locale.getLanguage());
            label = messageBundleService.findByKeyAndLanguage(key, locale.getLanguage()).getLabel();
            // element = new Element(bundleKey, label);
            //cache.put(element);
        } else {
            //LOGGER.debug("Getting message {} for language {} from CACHE", key, locale.getLanguage());
            //label = (String) element.getObjectValue();
        }
        return label;
    }

    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(messageBundleService.findAllKey());
    }
}
