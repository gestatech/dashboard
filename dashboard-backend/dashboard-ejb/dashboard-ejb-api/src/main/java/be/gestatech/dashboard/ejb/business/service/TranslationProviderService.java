package be.gestatech.dashboard.ejb.business.service;

import java.util.Map;

/**
 * Created by amurifa on 28/06/2017.
 */
public interface TranslationProviderService {

	Map<String, String> getTranslations(String language);

	Map<String, String> getReferenceDataTranslations(String referenceDataName, String language);
}
