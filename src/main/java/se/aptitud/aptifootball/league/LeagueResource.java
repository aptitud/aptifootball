package se.aptitud.aptifootball.league;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import se.aptitud.aptifootball.applicaton.AptiFootballConfig;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
        logger.debug("creting " + newLeague.name);
        return leagueRepository.create(newLeague).id;
    }

}
