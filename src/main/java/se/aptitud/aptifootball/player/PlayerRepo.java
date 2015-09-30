package se.aptitud.aptifootball.player;

import com.github.pabloo99.xmlsoccer.api.dto.GetPlayersResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetTeamResultDto;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import se.aptitud.aptifootball.AptiFootballRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static net.sf.ehcache.config.PersistenceConfiguration.Strategy.LOCALTEMPSWAP;

public class PlayerRepo extends AptiFootballRepo {

    public PlayerRepo(String key, String url) {
        super(key, url,
        new Cache(
                new CacheConfiguration("playerCache", 1000)
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                        .eternal(false)
                        .timeToLiveSeconds(600)
                        .timeToIdleSeconds(300)
                        .diskExpiryThreadIntervalSeconds(120)
                        .persistence(new PersistenceConfiguration().strategy(LOCALTEMPSWAP)))
        );

    }

    public PlayerRepo(String key, String url, Cache cache) {
        super(key, url, cache);
    }

    @SuppressWarnings("unchecked")
    public List<Player> players(String leagueId){
        Element players = cache.get(leagueId);
        List<Player>  playerCache = players == null?null: (List<Player>) players.getObjectValue();
        if(playerCache == null  ||  playerCache.isEmpty()) {
            playerCache = readPlayerList(leagueId);
            cache.put(new Element(leagueId, playerCache));
        }
        return playerCache;
    }

    private List<Player> readPlayerList(String leagueId) {
        List<GetTeamResultDto> allTeamsByLeagueAndSeason = xmlSoccerService.getAllTeamsByLeagueAndSeason(leagueId, "");
        List<List<Player>> players = allTeamsByLeagueAndSeason.stream().map(this::getPlayersFor).collect(toList());
        return players.stream().flatMap(Collection::stream).collect(toList());
    }

    private Player convertDtoToPlayer(GetPlayersResultDto dto) {
        return new Player(dto.getId(), dto.getName(), dto.getPosition(), dto.getPlayerNumber());
    }


    private List<Player> getPlayersFor(GetTeamResultDto team) {
        List<GetPlayersResultDto> playersByTeam = xmlSoccerService.getPlayersByTeam(team.getTeamId());
        return playersByTeam.stream().map(this::convertDtoToPlayer).collect(toList());
    }


}
