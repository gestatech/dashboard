package be.gestatech.dashboard.infra.cache.boundary.service.api;

import be.gestatech.dashboard.infra.cache.qualifier.LocalCacheProvider;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.io.Serializable;

public interface CacheService extends Serializable {

    String BEAN_NAME = "cacheService";

    CacheManager createCacheManager(String cacheName);

    Cache<String, Object> createUserCache(CacheManager cacheManager);
}
