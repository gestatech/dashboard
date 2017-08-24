package be.gestatech.dashboard.web.jsf.producer;

import be.gestatech.dashboard.web.jsf.qualifier.FacesContexProvider;
import be.gestatech.dashboard.web.jsf.qualifier.NavigationHandlerProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
public class NavigationHandlerProducer {

    @Inject
    @FacesContexProvider
    private FacesContext context;

    @Produces
    @RequestScoped
    @NavigationHandlerProvider
    public NavigationHandler getNavigationHandler() {
        NavigationHandler navigationHandler = null;
        if (Objects.nonNull(context)) {
            Application application = context.getApplication();
            if (Objects.nonNull(application)) {
                navigationHandler = application.getNavigationHandler();
            }
        }
        return navigationHandler;
    }
}
