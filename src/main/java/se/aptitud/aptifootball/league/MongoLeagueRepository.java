package se.aptitud.aptifootball.league;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import se.aptitud.aptifootball.AptiFootballRepo;
import se.aptitud.aptifootball.user.User;

import java.util.ArrayList;
import java.util.List;

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


    public List<League> leagues(String user) {
        Document doc = new Document("type", "league").append("creator", user);
        FindIterable<Document> documents = aptifootball.find(doc);
        List<League> leagues = new ArrayList<>();
        documents.spliterator().forEachRemaining( league -> leagues.add(createLeague(league)));
        return leagues;
    }


    public List<League> leagues() {
        Document doc = new Document("type", "league");
        FindIterable<Document> documents = aptifootball.find(doc);
        List<League> leagues = new ArrayList<>();
        documents.spliterator().forEachRemaining( league -> leagues.add(createLeague(league)));
        return leagues;
    }

    private League createLeague(Document storedLeague) {
        return new League(storedLeague.get("_id").toString(),storedLeague.get("creator").toString(), storedLeague.get("name").toString());

    }
}
