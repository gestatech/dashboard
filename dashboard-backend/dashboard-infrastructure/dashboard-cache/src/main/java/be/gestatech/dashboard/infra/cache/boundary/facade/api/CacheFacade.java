package be.gestatech.dashboard.infra.cache.boundary.facade.api;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.io.Serializable;

public interface CacheFacade extends Serializable {
    CacheManager getCacheManager(String cacheName);
}
