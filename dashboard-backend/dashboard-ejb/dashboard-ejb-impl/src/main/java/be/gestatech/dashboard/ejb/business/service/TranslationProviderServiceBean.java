package be.gestatech.dashboard.ejb.business.service;

import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Singleton;

/**
 * Created by amurifa on 28/06/2017.
 */
@Singleton
@Local(TranslationProviderService.class)
public class TranslationProviderServiceBean implements TranslationProviderService {

	@Override
	public Map<String, String> getTranslations(String language) {
		return null;
	}

	@Override
	public Map<String, String> getReferenceDataTranslations(String referenceDataName, String language) {
		return null;
	}
}
