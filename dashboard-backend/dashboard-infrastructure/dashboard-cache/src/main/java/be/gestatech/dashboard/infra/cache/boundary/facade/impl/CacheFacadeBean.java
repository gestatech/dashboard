package be.gestatech.dashboard.infra.cache.boundary.facade.impl;

import be.gestatech.dashboard.infra.cache.boundary.facade.api.CacheFacade;
import be.gestatech.dashboard.infra.cache.boundary.service.api.CacheService;

import javax.cache.CacheManager;
import javax.ejb.EJB;
import javax.ejb.Local;

@Local(value = CacheFacade.class)
public class CacheFacadeBean implements CacheFacade {

    @EJB
    private CacheService cacheService;

    @Override
    public CacheManager getCacheManager(String cacheName) {
        return cacheService.getCacheManager(cacheName);
    }
}
