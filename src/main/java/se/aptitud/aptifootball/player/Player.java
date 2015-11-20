package se.aptitud.aptifootball.player;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable{

    @JsonProperty
    public final int id;
    @JsonProperty
    public final String name;
    @JsonProperty
    public final Position position;
    @JsonProperty
    public final Integer playerNumber;
    @JsonProperty
    public final List<Score> scores;


    public Player(int id, String name, Position position, Integer playerNumber, List<Score> scores) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.playerNumber = playerNumber;
        this.scores = scores;
    }
}
