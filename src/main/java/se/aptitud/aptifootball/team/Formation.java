package se.aptitud.aptifootball.team;

/**
 * Created by jelmstrom on 03/11/15.
 */
public enum Formation {
    FOUR_FOUR_TWO(4,4,2),
    FOUR_THREE_THREE(4,3,3);

    public final int defenders;
    public final int midfielders;
    public final int forwards;
    public final int goalkeepers = 1;
    public final int substitutes = 3;

    Formation(int defenders, int midfielders, int forwards) {
        this.defenders = defenders;
        this.midfielders = midfielders;
        this.forwards = forwards;
    }
}
