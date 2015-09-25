package se.aptitud.aptifootball.player;

import com.github.pabloo99.xmlsoccer.api.dto.GetPlayersResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetTeamResultDto;
import se.aptitud.aptifootball.XmlSoccerRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PlayerRepo extends XmlSoccerRepo {


    public PlayerRepo(String key, String url) {
        super(key, url);
    }

    public List<Player> players(String leagueId){
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
