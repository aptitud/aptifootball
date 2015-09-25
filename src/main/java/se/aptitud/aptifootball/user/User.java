package se.aptitud.aptifootball.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jelmstrom on 25/09/15.
 */
public class User {

    @JsonProperty
    public final String id;

    public User(String id) {
        this.id = id;
    }
}
