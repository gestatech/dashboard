package be.gestatech.dashboard.infra.cache.boundary.facade.api;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.io.Serializable;

public interface CacheFacade extends Serializable {
    String BEAN_NAME = "cacheFacade";

    // cache names
    String I18N_CACHE = "i18n";

    CacheManager createCacheManager(String cacheName);

    Cache<String, Object> createUserCache(CacheManager cacheManager);

    Cache getCache(String cacheName);
}
