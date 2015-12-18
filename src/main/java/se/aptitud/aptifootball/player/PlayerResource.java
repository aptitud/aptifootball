package se.aptitud.aptifootball.player;

import com.codahale.metrics.annotation.Timed;
import se.aptitud.aptifootball.applicaton.AptiFootballConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("aptifootball/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {
    private final AptiFootballConfig config;

    public PlayerResource(AptiFootballConfig config) {
        this.config = config;
    }

    @GET
    @Path("{leagueId}")
    @Timed
    public List<Player> playersByLeague(@PathParam("leagueId") String leagueId) {
        return new ExternalPlayerRepo(config).players(leagueId);
    }

}
