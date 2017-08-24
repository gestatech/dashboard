package be.gestatech.dashboard.infra.cache.boundary.service.impl;

import be.gestatech.dashboard.infra.cache.boundary.service.api.CacheService;
import be.gestatech.dashboard.infra.cache.producer.CacheManagerProducer;
import be.gestatech.dashboard.infra.cache.producer.ObjectCacheProducer;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.ejb.EJB;
import javax.ejb.Local;

@Local(value = CacheService.class)
public class CacheServiceBean implements CacheService {

    private static final long serialVersionUID = 4529687752041023600L;

    @EJB
    private CacheManagerProducer cacheManagerProducer;

    @EJB
    private ObjectCacheProducer objectCacheProducer;

    public CacheServiceBean() {
    }

    @Override
    public CacheManager createCacheManager(String cacheName) {
        return cacheManagerProducer.createCacheManager();
    }

    @Override
    public Cache<String, Object> createUserCache(CacheManager cacheManager) {
        return objectCacheProducer.createUserCache(cacheManager);
    }
}
