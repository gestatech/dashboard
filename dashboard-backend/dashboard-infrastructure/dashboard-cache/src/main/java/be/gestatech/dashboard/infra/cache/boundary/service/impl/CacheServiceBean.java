package be.gestatech.dashboard.infra.cache.boundary.service.impl;

import be.gestatech.dashboard.infra.cache.boundary.service.api.CacheService;
import be.gestatech.dashboard.infra.cache.producer.CacheManagerProducer;

import javax.cache.CacheManager;
import javax.ejb.EJB;
import javax.ejb.Local;

@Local(value = CacheService.class)
public class CacheServiceBean implements CacheService {

    private static final long serialVersionUID = 4529687752041023600L;

    @EJB
    private CacheManagerProducer cacheManagerProducer;

    public CacheServiceBean() {
    }

    @Override
    public CacheManager getCacheManager(String cacheName) {
        return cacheManagerProducer.createCacheManager();
    }
}
