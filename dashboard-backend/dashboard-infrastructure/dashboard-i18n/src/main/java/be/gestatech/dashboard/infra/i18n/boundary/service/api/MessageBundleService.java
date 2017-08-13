package be.gestatech.dashboard.infra.i18n.boundary.service.api;

import be.gestatech.dashboard.infra.core.model.Page;
import be.gestatech.dashboard.infra.core.model.PageRequest;
import be.gestatech.dashboard.infra.i18n.entity.MessageBundle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by amurifa on 18/07/2017.
 */
public interface MessageBundleService extends Serializable{

	String BEAN_NAME = "messageBundleService";

	Page<MessageBundle> findPaginated(PageRequest somePageRequest);

	List<String> findAllKey();

	List<MessageBundle> findByKey(String someKey);

	MessageBundle findByKeyAndLanguage(String someKey, String someLanguage);

	MessageBundle save(MessageBundle someMessageBundle);

	void delete(MessageBundle someMessageBundle);
}
