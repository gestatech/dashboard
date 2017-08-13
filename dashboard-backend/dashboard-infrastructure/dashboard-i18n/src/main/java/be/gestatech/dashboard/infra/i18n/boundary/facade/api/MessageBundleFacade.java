package be.gestatech.dashboard.infra.i18n.boundary.facade.api;

import java.util.List;

import be.gestatech.dashboard.infra.core.model.*;
import be.gestatech.dashboard.infra.i18n.entity.MessageBundle;

/**
 * Created by amurifa on 18/07/2017.
 */
public interface MessageBundleFacade {

	String BEAN_NAME = "messageBundleFacade";

	Page<MessageBundle> findPaginated(PageRequest somePageRequest);

	List<String> findAllKey();

	List<MessageBundle> findByKey(String someKey);

	MessageBundle findByKeyAndLanguage(String someKey, String someLanguage);

	MessageBundle save(MessageBundle someMessageBundle);

	void delete(MessageBundle someMessageBundle);
}
