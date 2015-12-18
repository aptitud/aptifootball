package se.aptitud.aptifootball.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import se.aptitud.aptifootball.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class League implements Serializable{

    @JsonProperty
    public final String id;
    @JsonProperty
    public final String creator;
    @JsonProperty
    public final String name;

    public League() {
        id="";
        creator="";
        name="";
    }

    public League(String id, String creator, String name) {
        this.id = id;
        this.creator = creator;
        this.name = name;
    }



}
