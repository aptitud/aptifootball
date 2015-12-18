package se.aptitud.aptifootball.league;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import se.aptitud.aptifootball.applicaton.AptiFootballConfig;
import se.aptitud.aptifootball.user.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("aptifootball/league")
@Produces(MediaType.APPLICATION_JSON)
public class LeagueResource {
    private final Logger logger = LoggerFactory.getLogger(LeagueResource.class);
    private LeagueRepo leagueRepository;

    public LeagueResource(AptiFootballConfig config) {
        leagueRepository = new MongoLeagueRepository(config.getAccessKey(), config.getUrl(), config.getDatabaseUrl());
    }

    @POST
    public String newLeague(@RequestBody League newLeague){
        logger.debug("creating " + newLeague.name);
        return leagueRepository.create(newLeague).id;
    }


    @GET
    @Path("{creator}")
    @Timed
    public List<League> userLeagues(@PathParam("creator") String creator){
        logger.debug("reading leagues from  " + creator);
        return leagueRepository.leagues(creator);
    }

    @GET
    @Path("list")
    @Timed
    public List<League> allLeagues(){
        logger.debug("reading leagues");
        return leagueRepository.leagues();
    }

}
