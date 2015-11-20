package se.aptitud.aptifootball.user;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import se.aptitud.aptifootball.AptiFootballRepo;

import static net.sf.ehcache.config.PersistenceConfiguration.Strategy.LOCALTEMPSWAP;

public class UserRepo extends AptiFootballRepo {

    public static final String USER_CACHE = "userCache";
    private final String cacheName;

    public UserRepo(String key, String url, Cache newCache) {
        super(key, url, newCache);
        cacheName = newCache.getName();
    }

    public UserRepo(String key, String url) {
        this(key, url, new Cache(
                        new CacheConfiguration(USER_CACHE, 1000)
                                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                                .eternal(false)
                                .timeToLiveSeconds(600)
                                .timeToIdleSeconds(300)
                                .diskExpiryThreadIntervalSeconds(120)
                                .persistence(new PersistenceConfiguration().strategy(LOCALTEMPSWAP)))
        );
    }

    public long addUser(User user){
        Cache cache = manager.getCache(USER_CACHE);
        cache.put(new Element(user.id, user));
        return user.id;

    }

}
