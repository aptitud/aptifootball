package se.aptitud.aptifootball.user;

import com.codahale.metrics.annotation.Timed;
import se.aptitud.aptifootball.player.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("aptifootball/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    @Path("list")
    @Timed
    public List<User> listUsers(){
        return Collections.singletonList(new User("1231241", "Nisse", Collections.singletonList(new Player(231241, "neymar", "cf", 9))));
    }

}
