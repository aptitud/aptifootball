package se.aptitud.aptifootball;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import se.aptitud.aptifootball.player.Player;
import se.aptitud.aptifootball.player.PlayerRepo;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class Players {

    public static final String PLAYERS = "testPlayers";
    private MongoDatabase db;
    private MongoClient mongoClient;

    @Before
    public void setUp() {

        mongoClient = new MongoClient("192.168.59.103", 27017);
        db = mongoClient.getDatabase("aptifootball");
        db.createCollection(PLAYERS);

    }
    @After
    public void tearDown(){
        db.getCollection(PLAYERS).drop();
    }


    @Test
    @Ignore
    public void getAllPlayers(){
        PlayerRepo repo = new PlayerRepo(System.getProperty("key"), System.getProperty("url"));
        List<Player> players = repo.players("3");
        assertThat(players.size(), is(not(0)));
    }

    @Test
    public void playersAreSavedWithInternalId(){
        MongoCollection<Document> players = db.getCollection(PLAYERS);
        Document player = new Document("value", "data");
        players.insertOne(player);
        long count = players.count();
        assertThat(count, is(1L));
        FindIterable<Document> documents = players.find(new Document("value", "data"));
        documents.forEach((Block<Document>) document -> {
            assertThat(document.get("_id"), not(nullValue()));
        });


    }





}
