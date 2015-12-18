package se.aptitud.aptifootball.league;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import se.aptitud.aptifootball.AptiFootballRepo;

/**
 * Created by jelmstrom on 18/12/15.
 */
public class MongoLeagueRepository extends AptiFootballRepo implements LeagueRepo {

    public MongoLeagueRepository(String apiKey, String serviceUrl, String dbUrl) {
        super(apiKey, serviceUrl, dbUrl);
    }

    @Override
    public League create(League league) {
        Document doc = new Document("name", league.name)
                .append("type", "league")
                .append("creator", league.creator);
        aptifootball.insertOne(doc);
        FindIterable<Document> documents = aptifootball.find(doc);
        Document storedUser = documents.iterator().next();
        return createLeague(storedUser);

    }

    private League createLeague(Document storetLeague) {
        return new League(storetLeague.get("id").toString(),storetLeague.get("cretor").toString(), storetLeague.get("name").toString());

    }
}
