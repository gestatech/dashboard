package be.gestatech.dashboard.infra.i18n.boundary.facade.impl;

import be.gestatech.dashboard.infra.core.model.Page;
import be.gestatech.dashboard.infra.core.model.PageRequest;
import be.gestatech.dashboard.infra.i18n.boundary.facade.api.MessageBundleFacade;
import be.gestatech.dashboard.infra.i18n.entity.MessageBundle;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by amurifa on 18/07/2017.
 */
@Local(value = MessageBundleFacade.class)
public class MessageBundleFacadeBean implements MessageBundleFacade {

	@Override
	public Page<MessageBundle> findPaginated(PageRequest somePageRequest) {
		return null;
	}

	@Override
	public List<String> findAllKey() {
		return null;
	}

	@Override
	public List<MessageBundle> findByKey(String someKey) {
		return null;
	}

	@Override
	public MessageBundle findByKeyAndLanguage(String someKey, String someLanguage) {
		return null;
	}

	@Override
	public MessageBundle save(MessageBundle someMessageBundle) {
		return null;
	}

	@Override
	public void delete(MessageBundle someMessageBundle) {

	}
}
