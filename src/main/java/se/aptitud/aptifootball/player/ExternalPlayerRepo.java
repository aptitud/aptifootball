package se.aptitud.aptifootball.player;

import com.github.pabloo99.xmlsoccer.api.dto.GetPlayersResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetTeamResultDto;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import se.aptitud.aptifootball.AptiFootballRepo;
import se.aptitud.aptifootball.applicaton.AptiFootballConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static net.sf.ehcache.config.PersistenceConfiguration.Strategy.LOCALTEMPSWAP;

public class ExternalPlayerRepo extends AptiFootballRepo {

    public ExternalPlayerRepo(AptiFootballConfig config) {
       super(config);
    }


    @SuppressWarnings("unchecked")
    public List<Player> players(String leagueId){
        return readPlayerList(leagueId);
    }

    private List<Player> readPlayerList(String leagueId) {
        List<GetTeamResultDto> allTeamsByLeagueAndSeason = xmlSoccerService.getAllTeamsByLeagueAndSeason(leagueId, "");
        List<List<Player>> players = allTeamsByLeagueAndSeason.stream().map(this::getPlayersFor).collect(toList());
        return players.stream().flatMap(Collection::stream).collect(toList());
    }

    private Player convertDtoToPlayer(GetPlayersResultDto dto) {
        return new Player(dto.getId(), dto.getName(), Position.valueOf(dto.getPosition().toUpperCase()), dto.getPlayerNumber(), Collections.EMPTY_LIST);
    }


    private List<Player> getPlayersFor(GetTeamResultDto team) {
        List<GetPlayersResultDto> playersByTeam = xmlSoccerService.getPlayersByTeam(team.getTeamId());
        return playersByTeam.stream().map(this::convertDtoToPlayer).collect(toList());
    }


}
