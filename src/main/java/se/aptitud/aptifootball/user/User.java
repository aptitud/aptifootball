package se.aptitud.aptifootball.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import se.aptitud.aptifootball.player.Player;

import java.util.List;

/**
 * Created by jelmstrom on 25/09/15.
 */
public class User {

    @JsonProperty
    public final String id;

    @JsonProperty
    public final String username;

    @JsonProperty
    public final List<Player> players;

    public User(String id, String username, List<Player> players) {
        this.id = id;
        this.username = username;
        this.players = players;
    }
}
