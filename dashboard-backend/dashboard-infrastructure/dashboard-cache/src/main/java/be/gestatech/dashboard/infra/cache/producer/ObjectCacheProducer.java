package be.gestatech.dashboard.infra.cache.producer;

import be.gestatech.dashboard.infra.cache.qualifier.LocalCacheProvider;
import be.gestatech.dashboard.infra.cache.qualifier.ObjectCache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class ObjectCacheProducer {

    private static final String CACHE_NAME = System.getProperty("jcache.cache.name", "object_cache");
    public static final int DURATION = Integer.parseInt(System.getProperty("jcache.cache.duration", "60"));

    @Produces
    @Singleton
    @ObjectCache
    public Cache<String, Object> createUserCache(@LocalCacheProvider final CacheManager cacheManager) {

        final MutableConfiguration<String, Object> config = new MutableConfiguration<>();
        config.setStoreByValue(true)
                .setTypes(String.class, Object.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(TimeUnit.MINUTES, DURATION)))
                .setStatisticsEnabled(true);
        return cacheManager.createCache(CACHE_NAME, config);
    }
}
