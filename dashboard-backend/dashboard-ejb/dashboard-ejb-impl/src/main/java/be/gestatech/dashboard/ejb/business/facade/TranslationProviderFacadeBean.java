package be.gestatech.dashboard.ejb.business.facade;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.gestatech.dashboard.ejb.business.service.TranslationProviderService;
import be.gestatech.dashboard.ejb.business.vo.Translation;
import be.gestatech.dashboard.infra.cash.Cachable;

/**
 * Created by amurifa on 28/06/2017.
 */
@Stateless
@Local(TranslationProviderFacade.class)
public class TranslationProviderFacadeBean implements TranslationProviderFacade, Cachable {

	private static final Logger LOGGER = Logger.getLogger(TranslationProviderFacadeBean.class.getName());

	@Override
	public void onRefresh() {

	}

	@Override
	public List<Translation> getTranslations(String language) {
		return null;
	}

	@Override
	public Map<String, List<Translation>> getReferenceDataTranslations(String referenceDataName) {
		return null;
	}

	@Override
	public ResourceBundle getTranslationsAsResourceBundle(String language) {
		return null;
	}
}
