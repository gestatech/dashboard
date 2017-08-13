package be.gestatech.dashboard.resources;

import java.io.Serializable;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * Abstract class that contains common methods needed for ResourceBundles in the system
 * Created by amurifa on 30/06/2017.
 */
public abstract class LocalizedResource extends ListResourceBundle implements Serializable {

	private Locale locale;

	public LocalizedResource() {
		Locale defaultLocale;
		if(FacesContext.getCurrentInstance() != null){
			defaultLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		}else{
			defaultLocale = Locale.getDefault();
		}
		setParent(ResourceBundle.getBundle(getBundleName(), defaultLocale, new ResourceBundleDatabaseControl()));
	}

	public LocalizedResource(Locale locale) {
		setParent(ResourceBundle.getBundle(getBundleName(), locale, new ResourceBundleDatabaseControl()));
		this.locale = locale;
	}

	@Override
	protected Object[][] getContents() {
		return ((ResourceBundleDatabaseControl.ResourceService)parent).getContents();
	}

	abstract String getBundleName();

}
