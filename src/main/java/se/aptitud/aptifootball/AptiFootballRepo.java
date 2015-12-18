package se.aptitud.aptifootball;

import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.bson.Document;

public class AptiFootballRepo {

    protected XmlSoccerService xmlSoccerService;
    protected static CacheManager manager = CacheManager.create();

    protected final MongoClient mongoClient;
    protected final MongoDatabase db;
    protected final MongoCollection<Document> aptifootball;


    public AptiFootballRepo(String apiKey, String serviceUrl, String dbUrl) {
        this.xmlSoccerService = new XmlSoccerServiceImpl();
        xmlSoccerService.setApiKey(apiKey);
        xmlSoccerService.setServiceUrl(serviceUrl);
        mongoClient = new MongoClient(new MongoClientURI(dbUrl));
        db = mongoClient.getDatabase("web_cv");
        getOrInitCollection();
        this.aptifootball = getOrInitCollection();
    }

    private MongoCollection<Document> getOrInitCollection() {
        MongoCollection<Document> aptifootballCollection = db.getCollection("aptifootball");
        if(aptifootballCollection == null ){
            db.createCollection("aptifootball");
            aptifootballCollection  = db.getCollection("aptifootball");
        }

        return  aptifootballCollection;
    }


    public AptiFootballRepo(String apiKey, String serviceUrl, Cache newCache) {
        this(apiKey, serviceUrl, "mongodb://localhost:27017");
        Cache existing = manager.getCache(newCache.getName());
        if(existing == null) {
            manager.addCache(newCache);
        }
    }


}
