package se.aptitud.aptifootball;

import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.sf.ehcache.CacheManager;
import org.bson.Document;
import se.aptitud.aptifootball.applicaton.AptiFootballConfig;

public class AptiFootballRepo {

    protected XmlSoccerService xmlSoccerService;

    protected final MongoClient mongoClient;
    protected final MongoDatabase db;
    protected final MongoCollection<Document> aptifootball;

    public AptiFootballRepo(AptiFootballConfig config) {
        this.xmlSoccerService = new XmlSoccerServiceImpl();
        xmlSoccerService.setApiKey(config.getAccessKey());
        xmlSoccerService.setServiceUrl(config.getUrl());
        mongoClient = new MongoClient(new MongoClientURI(config.getDatabaseUrl()));
        db = mongoClient.getDatabase("web_cv");
        getOrInitCollection("aptifootball");
        this.aptifootball = getOrInitCollection(config.getCollectionName());
    }


    public AptiFootballRepo(String apiKey, String serviceUrl, String dbUrl) {
        this.xmlSoccerService = new XmlSoccerServiceImpl();
        xmlSoccerService.setApiKey(apiKey);
        xmlSoccerService.setServiceUrl(serviceUrl);
        mongoClient = new MongoClient(new MongoClientURI(dbUrl));
        db = mongoClient.getDatabase("web_cv");
        getOrInitCollection("aptifootball");
        this.aptifootball = getOrInitCollection("aptifootball");
    }

    private MongoCollection<Document> getOrInitCollection(String collectionName) {
        MongoCollection<Document> aptifootballCollection = db.getCollection(collectionName);
        if(aptifootballCollection == null ){
            db.createCollection(collectionName);
            aptifootballCollection  = db.getCollection(collectionName);
        }

        return  aptifootballCollection;
    }


}
