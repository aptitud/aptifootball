package se.aptitud.aptifootball;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.aptitud.aptifootball.player.Player;
import se.aptitud.aptifootball.player.PlayerRepo;

import java.util.List;

import static net.sf.ehcache.config.PersistenceConfiguration.Strategy.LOCALTEMPSWAP;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class Players {

    private Cache testCache;


    @Before
    public void setUp() {
        testCache = new Cache(
                new CacheConfiguration("testPlayerCache", 1000)
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                        .eternal(false)
                        .timeToLiveSeconds(600)
                        .timeToIdleSeconds(300)
                        .diskExpiryThreadIntervalSeconds(120)
                        .persistence(new PersistenceConfiguration().strategy(LOCALTEMPSWAP)));
    }

    @After
    public void tearDown() {
        testCache.getCacheManager().removeCache("testPlayerCache");
    }


    @Test
    public void getAllPlayers() {
        PlayerRepo repo = new PlayerRepo(System.getProperty("key"), System.getProperty("url"), testCache);
        List<Player> players = repo.players("3");
        assertThat(players.size(), is(not(0)));

        players = repo.players("3");
        assertThat(players.size(), is(not(0)));
    }


    @Test
    public void itemsShouldBeCahced() {
        PlayerRepo repo = new PlayerRepo(System.getProperty("key"), System.getProperty("url"), testCache);
        repo.players("3");
        assertThat(testCache.getKeys().isEmpty(), is(false));
    }


}
