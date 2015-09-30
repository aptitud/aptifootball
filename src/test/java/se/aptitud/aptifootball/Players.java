package se.aptitud.aptifootball;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.bson.BsonDocument;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import se.aptitud.aptifootball.player.Player;
import se.aptitud.aptifootball.player.PlayerRepo;
import java.util.List;

import static net.sf.ehcache.config.PersistenceConfiguration.Strategy.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class Players {

    public static final String PLAYERS = "testPlayers";
    private Cache testCache;


    @Before
    public void setUp() {
        CacheManager cacheManager = CacheManager.create();
        testCache = new Cache(
                new CacheConfiguration("testPlayerCache", 1000)
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                        .eternal(false)
                        .timeToLiveSeconds(600)
                        .timeToIdleSeconds(300)
                        .diskExpiryThreadIntervalSeconds(120)
                        .persistence(new PersistenceConfiguration().strategy(LOCALTEMPSWAP)));

        cacheManager.addCache(testCache);

    }



    @Test
    public void getAllPlayers(){
        PlayerRepo repo = new PlayerRepo(System.getProperty("key"), System.getProperty("url"), testCache);
        List<Player> players = repo.players("3");
        assertThat(players.size(), is(not(0)));

        players = repo.players("3");
        assertThat(players.size(), is(not(0)));
    }


    @Test
    public void itemsShouldBeCahced(){


        PlayerRepo repo = new PlayerRepo(System.getProperty("key"), System.getProperty("url"), testCache );
        repo.players("3");
        assertThat(testCache.getKeys().isEmpty(), is(false));
    }


}
