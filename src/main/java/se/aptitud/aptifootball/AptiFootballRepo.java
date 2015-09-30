package se.aptitud.aptifootball;

import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class AptiFootballRepo {

    protected XmlSoccerService xmlSoccerService;
    protected Cache cache;
    private static CacheManager manager = CacheManager.create();


    public AptiFootballRepo(String key, String url) {
        this.xmlSoccerService = new XmlSoccerServiceImpl();
        xmlSoccerService.setApiKey(key);
        xmlSoccerService.setServiceUrl(url);
    }

    public AptiFootballRepo(String key, String url, Cache newCache) {
        this(key, url);
        Cache existing = manager.getCache(newCache.getName());
        if(existing == null) {
            this.cache = newCache;
            manager.addCache(this.cache);
        } else {
            this.cache = existing;
        }
    }
}
