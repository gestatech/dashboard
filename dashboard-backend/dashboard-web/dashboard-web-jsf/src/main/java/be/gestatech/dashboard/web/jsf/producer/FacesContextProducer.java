package be.gestatech.dashboard.web.jsf.producer;

import be.gestatech.dashboard.web.jsf.qualifier.FacesContexProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

@ApplicationScoped
public class FacesContextProducer {

    @Produces
    @RequestScoped
    @FacesContexProvider
    public FacesContext getFacesContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            throw new ContextNotActiveException("FacesContext is not active");
        }
        return facesContext;
    }
}
