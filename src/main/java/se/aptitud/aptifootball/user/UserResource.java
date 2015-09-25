package se.aptitud.aptifootball.user;

import com.codahale.metrics.annotation.Timed;

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
    @Timed
    public List<User> listUsers(){
        return Collections.singletonList(new User("1231241"));
    }

}
