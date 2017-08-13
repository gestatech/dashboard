package be.gestatech.dashboard.infra.cache.boundary.service.api;

import javax.cache.CacheManager;
import java.io.Serializable;

public interface CacheService extends Serializable {

    String BEAN_NAME = "cacheService";

    // cache names
    String I18N_CACHE = "i18n";

    CacheManager getCacheManager(String cacheName);
}
