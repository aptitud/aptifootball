package se.aptitud.aptifootball;

import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import net.sf.ehcache.Cache;

public class AptiFootballRepo {

    protected XmlSoccerService xmlSoccerService;
    protected Cache cache;




    public AptiFootballRepo(String key, String url) {
        this.xmlSoccerService = new XmlSoccerServiceImpl();
        xmlSoccerService.setApiKey(key);
        xmlSoccerService.setServiceUrl(url);
    }

    public AptiFootballRepo(String key, String url, Cache testCache) {
        this(key, url);
        this.cache=testCache;
    }
}
