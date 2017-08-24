package be.gestatech.dashboard.infra.cache.boundary.facade.impl;

import be.gestatech.dashboard.infra.cache.boundary.facade.api.CacheFacade;
import be.gestatech.dashboard.infra.cache.boundary.service.api.CacheService;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.ejb.EJB;
import javax.ejb.Local;

@Local(value = CacheFacade.class)
public class CacheFacadeBean implements CacheFacade {

    @EJB
    private CacheService cacheService;

    @Override
    public CacheManager createCacheManager(String cacheName) {
        return cacheService.createCacheManager(cacheName);
    }

    @Override
    public Cache<String, Object> createUserCache(CacheManager cacheManager) {
        return cacheService.createUserCache(cacheManager);
    }

    @Override
    public Cache getCache(String cacheName) {
        //TODO: review with CaheManager as String parameter
        CacheManager cacheManager = createCacheManager(cacheName);
        return  cacheManager.getCache(cacheName);
    }
}
