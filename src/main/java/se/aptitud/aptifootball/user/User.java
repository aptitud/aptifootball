package se.aptitud.aptifootball.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import se.aptitud.aptifootball.team.Team;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toMap;

public class User implements Serializable{

    @JsonProperty(required =false)
    public final long id;

    @JsonProperty
    public final String username;

    @JsonProperty
    public final String email;

    @JsonProperty(required = false)
    public final List<Team> teams;


    public User() {
        this.id = -1;
        this.username = "";
        this.email = "";
        this.teams = Collections.emptyList();
    }

    public User(long id, String username, String email, List<Team> teams) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.teams = teams;
    }

}
