package be.gestatech.dashboard.infra.cache.producer;

import be.gestatech.dashboard.infra.cache.qualifier.LocalCacheProvider;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@ApplicationScoped
public class CacheManagerProducer {

    @Produces
    @Singleton
    @LocalCacheProvider
    public CacheManager createCacheManager() {
        // property can be extracted to server configuration
        System.setProperty("hazelcast.jcache.provider.type", "server");
        return Caching.getCachingProvider().getCacheManager();
    }

    public void close(@Disposes @LocalCacheProvider final CacheManager instance) {
        if (!instance.isClosed()) {
            instance.close();
        }
    }
}
