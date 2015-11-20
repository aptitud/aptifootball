package se.aptitud.aptifootball.team;

import org.junit.Before;
import org.junit.Test;
import se.aptitud.aptifootball.player.Player;
import se.aptitud.aptifootball.player.Position;
import se.aptitud.aptifootball.player.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by jelmstrom on 03/11/15.
 */
public class TeamTest {

    private Team team;
    private List<Score> score;

    @Before
    public void setUp() throws Exception {
        team = new Team(1, 1, new ArrayList<Player>(), Formation.FOUR_FOUR_TWO);
        score = Collections.singletonList(new Score(1, 1));


    }

    @Test
    public void canOnlyAddOneGoalkeeper() throws Exception {

        validatePosition(team.formation.goalkeepers, Position.GOALKEEPER);
    }

    @Test
    public void canOnlyAddFourDefenders() throws Exception {

        validatePosition(team.formation.defenders, Position.DEFENDER);
    }

    @Test
    public void canOnlyAddFourMidfielders() throws Exception {

        validatePosition(team.formation.midfielders, Position.MIDFIELD);
    }

    @Test
    public void canOnlyAddTwoForwards() throws Exception {

        validatePosition(team.formation.forwards, Position.FORWARD);
    }

    @Test
    public void canOnlyAddThreeSubs() throws Exception {
        validatePosition(team.formation.substitutes, Position.SUBSTITUTE);
    }

    @Test
    public void checkPointsAre4(){

        validatePosition(team.formation.midfielders, Position.MIDFIELD);
        assertThat(team.score(), is (4l));

    }

    private void validatePosition(int count, Position position) {
        for(int i = 0; i < count; i++ ){
            Player player = new Player(i+1, "test", position, 1, score);
            assertThat(team.addPlayer(player), is(true));
        }
        Player player2 = new Player(10, "test", position, 1, score);
        assertThat(team.addPlayer(player2), is(false));
    }
}