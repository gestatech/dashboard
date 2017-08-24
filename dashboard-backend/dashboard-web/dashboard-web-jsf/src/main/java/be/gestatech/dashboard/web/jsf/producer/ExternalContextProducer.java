package be.gestatech.dashboard.web.jsf.producer;

import be.gestatech.dashboard.web.jsf.qualifier.ExternalContextProvider;
import be.gestatech.dashboard.web.jsf.qualifier.FacesContexProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ApplicationScoped
public class ExternalContextProducer {

    @Named
    @Produces
    @RequestScoped
    @ExternalContextProvider
    public ExternalContext getExternalContext(@FacesContexProvider final FacesContext facesContext) {
        if (facesContext == null) {
            throw new ContextNotActiveException("FacesContext is not active");
        }
        return facesContext.getExternalContext();
    }
}
