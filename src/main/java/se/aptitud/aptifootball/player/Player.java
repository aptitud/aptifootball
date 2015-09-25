package se.aptitud.aptifootball.player;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

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
