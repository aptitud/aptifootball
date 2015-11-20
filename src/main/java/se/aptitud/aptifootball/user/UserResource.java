package se.aptitud.aptifootball.user;

import com.codahale.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.RequestBody;
import se.aptitud.aptifootball.applicaton.AptiFootballConfig;
import se.aptitud.aptifootball.player.Player;
import se.aptitud.aptifootball.player.Position;
import se.aptitud.aptifootball.player.Score;
import se.aptitud.aptifootball.team.Formation;
import se.aptitud.aptifootball.team.Team;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Collections.singletonList;

@Path("aptifootball/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {


    private final AptiFootballConfig config;
    private final UserRepo repo;

    public UserResource(AptiFootballConfig config) {
        this.config = config;
        this.repo = new UserRepo(config.getAccessKey(), config.getUrl());
    }

    @GET
    @Path("list")
    @Timed
    public List<User> listUsers(){
        int id = 1231241;
        Team team = new Team(1, id, singletonList(new Player(231241, "neymar", Position.FORWARD, 9, singletonList(new Score(1, 10)))), Formation.FOUR_FOUR_TWO);
        return singletonList(new User(id, "Nisse", "", singletonList(team)));
    }

    @POST
    @Timed
    public User create(@RequestBody User user){
        User newUser = new User(new Random().nextLong(), user.username, user.email, Collections.emptyList());
        repo.addUser(newUser);
        return newUser;
    }

}
