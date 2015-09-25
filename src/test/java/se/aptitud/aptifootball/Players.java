package se.aptitud.aptifootball;

import org.junit.Test;
import se.aptitud.aptifootball.player.Player;
import se.aptitud.aptifootball.player.PlayerRepo;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class Players {

    @Test
    public void getAllPLayers(){

        PlayerRepo repo = new PlayerRepo(System.getProperty("key"), System.getProperty("url"));
        List<Player> players = repo.players("3");
        assertThat(players.size(), is(not(0)));
    }



}
