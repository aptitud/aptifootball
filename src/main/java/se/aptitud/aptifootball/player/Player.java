package se.aptitud.aptifootball.player;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Player implements Serializable{

    @JsonProperty
    public final int id;
    @JsonProperty
    public final String name;
    @JsonProperty
    public final String position;
    @JsonProperty
    public final Integer playerNumber;





    public Player(int id, String name, String position, Integer playerNumber) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.playerNumber = playerNumber;
    }
}
