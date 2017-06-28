package be.gestatech.dashboard.ejb.business.facade;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import be.gestatech.dashboard.ejb.business.vo.Translation;

/**
 * Created by amurifa on 28/06/2017.
 */
public interface TranslationProviderFacade {

	List<Translation> getTranslations(String language);

	Map<String, List<Translation>> getReferenceDataTranslations(String referenceDataName);

	ResourceBundle getTranslationsAsResourceBundle(String language);

}
