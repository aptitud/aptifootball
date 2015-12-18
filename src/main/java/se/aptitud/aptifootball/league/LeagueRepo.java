package se.aptitud.aptifootball.league;

import java.util.List;

/**
 * Created by jelmstrom on 18/12/15.
 */
public interface LeagueRepo {

    League create(League league);
    List<League> leagues(String user);

    List<League> leagues();
}
