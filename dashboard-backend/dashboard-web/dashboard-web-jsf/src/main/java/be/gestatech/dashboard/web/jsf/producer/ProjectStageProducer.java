package be.gestatech.dashboard.web.jsf.producer;

import be.gestatech.dashboard.web.jsf.qualifier.FacesContexProvider;
import be.gestatech.dashboard.web.jsf.qualifier.ProjectStageProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.inject.Produces;
import javax.faces.application.ProjectStage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ApplicationScoped
public class ProjectStageProducer {

    @Named
    @Produces
    @ProjectStageProvider
    public ProjectStage getProjectStage(@FacesContexProvider final FacesContext facesContext) {
        if (facesContext == null) {
            throw new ContextNotActiveException("FacesContext is not active");
        }
        return facesContext.getApplication().getProjectStage();
    }
}
