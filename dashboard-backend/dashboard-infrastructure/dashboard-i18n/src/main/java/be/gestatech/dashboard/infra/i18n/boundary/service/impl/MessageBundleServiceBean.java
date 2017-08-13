package be.gestatech.dashboard.infra.i18n.boundary.service.impl;

import be.gestatech.dashboard.infra.cache.qualifier.ObjectCache;
import be.gestatech.dashboard.infra.core.model.Page;
import be.gestatech.dashboard.infra.core.model.PageRequest;
import be.gestatech.dashboard.infra.i18n.boundary.service.api.MessageBundleService;
import be.gestatech.dashboard.infra.i18n.entity.MessageBundle;
import be.gestatech.dashboard.infra.i18n.entity.MessageBundleKey;

import javax.cache.Cache;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by amurifa on 18/07/2017.
 */
@Local(value = MessageBundleService.class)
public class MessageBundleServiceBean implements MessageBundleService {

    private static final long serialVersionUID = -7491456291261760774L;

    private static final Logger LOGGER = Logger.getLogger(MessageBundleService.class.getName());

    @PersistenceContext(unitName = "DASHBOARD_UNIT")
    private transient EntityManager entityManager;

    @Inject
    @ObjectCache
    private Cache cache;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Page<MessageBundle> findPaginated(final PageRequest somePageRequest) {
        List<MessageBundle> messageBundles = entityManager.createQuery(buildQuery(somePageRequest))
                .setFirstResult(somePageRequest.getStart())
                .setMaxResults(somePageRequest.getPageSize())
                .getResultList();

        List<MessageBundle> result = new ArrayList<MessageBundle>();
        for (MessageBundle messageBundle : messageBundles) {
            result.add(messageBundle);
            MessageBundle defaultMessageBundle;
            try {
                defaultMessageBundle = (MessageBundle) entityManager.createQuery("select mb from MessageBundle mb where mb.id.key = :key and mb.id.language = :language")
                        .setParameter("key", messageBundle.getId().getKey())
                        .setParameter("language", somePageRequest.getFilters().get(MessageBundle.LANGUAGE_FIELD))
                        .getSingleResult();
            } catch (NoResultException nre) {
                defaultMessageBundle = null;
            }
            if (defaultMessageBundle != null) {
                result.add(defaultMessageBundle);
            }
        }

        return new Page<MessageBundle>(result, count(somePageRequest));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<String> findAllKey() {
        return (List<String>) entityManager.createQuery("select distinct mb.id.key from MessageBundle mb");
    }

    @Override

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<MessageBundle> findByKey(final String someKey) {
        return entityManager.createQuery("select mb from MessageBundle mb where mb.id.key = :key")
                .setParameter("key", someKey)
                .getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MessageBundle findByKeyAndLanguage(final String someKey, final String someLanguage) {
        MessageBundle mb;
        final String qlString = "select mb from MessageBundle mb where mb.id.key = :key and mb.id.language = :language";
        try {
            mb = (MessageBundle) entityManager.createQuery(qlString)
                    .setParameter("key", someKey)
                    .setParameter("language", someLanguage)
                    .getSingleResult();
        } catch (NoResultException nre) {
            try {
                mb = (MessageBundle) entityManager.createQuery(qlString)
                        .setParameter("key", someKey)
                        .setParameter("language", "en")
                        .getSingleResult();
            } catch (NoResultException nre2) {
                mb = new MessageBundle();
                MessageBundleKey id = new MessageBundleKey(someKey, someLanguage);
                mb.setId(id);
                mb.setLabel(someKey);
            }
        }
        return mb;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MessageBundle save(final MessageBundle someMessageBundle) {
        //Clearing the cache
        //CacheManager cacheManager = CacheManager.create();
        cache.remove(someMessageBundle.getId());
        //Saving the changes to the message bundle
        MessageBundle mb = null;
        try {
            mb = (MessageBundle) entityManager.createQuery("select mb from MessageBundle mb where mb.id = :id")
                    .setParameter("id", someMessageBundle.getId())
                    .getSingleResult();
        } catch (NoResultException nre) {
            LOGGER.info("new message bundle");
        }
        if (mb == null) {
            entityManager.persist(someMessageBundle);
            return someMessageBundle;
        } else {
            return entityManager.merge(someMessageBundle);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(final MessageBundle someMessageBundle) {
        //Clearing the cache
        //CacheManager cacheManager = CacheManager.create();
        //Cache cache = cacheManager.getCache("i18n");
        cache.remove(someMessageBundle.getId());
        //Deleting message bundle
        MessageBundle messageBundle = entityManager.find(MessageBundle.class, someMessageBundle.getId());
        if (messageBundle != null) {
            messageBundle = entityManager.merge(someMessageBundle);
            entityManager.remove(messageBundle);
        }
    }

    private String buildQuery(final PageRequest somePageRequest) {
        StringBuilder builder = new StringBuilder(300);
        builder.append("select mb from MessageBundle mb where 1=1 and mb.id.language = 'en'");
        //Add filters
        if (somePageRequest.getFilters().containsKey(MessageBundle.KEY_FIELD)) {
            builder.append(" and mb.id.key like '%").append(somePageRequest.getFilters().get(MessageBundle.KEY_FIELD)).append("%' ");
        }
        if (somePageRequest.getFilters().containsKey(MessageBundle.VALUE_FIELD)) {
            builder.append(" and mb.label like '%").append(somePageRequest.getFilters().get(MessageBundle.VALUE_FIELD)).append("%' ");
        }
        //Add order by directive
        if (MessageBundle.KEY_FIELD.equals(somePageRequest.getSortField())) {
            builder.append(" order by mb.id.key ");
        }
        //Add sort order
        if (somePageRequest.getSortField() != null) {
            builder.append(somePageRequest.getSortOrder());
        }
        return builder.toString();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private int count(final PageRequest somePageRequest) {
        StringBuilder builder = new StringBuilder(200);
        builder.append("select count(distinct mb.id.key) from MessageBundle mb where 1=1 and mb.id.language = 'en' ");
        //Add filters
        if (somePageRequest.getFilters().containsKey(MessageBundle.KEY_FIELD)) {
            builder.append(" and mb.id.key like '%").append(somePageRequest.getFilters().get(MessageBundle.KEY_FIELD)).append("%' ");
        }
        if (somePageRequest.getFilters().containsKey(MessageBundle.VALUE_FIELD)) {
            builder.append(" and mb.label like '%").append(somePageRequest.getFilters().get(MessageBundle.VALUE_FIELD)).append("%' ");
        }
        return ((Long) entityManager.createQuery(builder.toString()).getSingleResult()).intValue();
    }

}
