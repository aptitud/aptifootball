package se.aptitud.aptifootball.team;

import se.aptitud.aptifootball.player.Player;
import se.aptitud.aptifootball.player.Position;

import java.util.List;
import static java.util.stream.Collectors.summarizingInt;


public class Team {
    public final int round;
    public final long userId;
    public final List<Player> players;
    public final Formation formation;



    public Team(int round, long userId, List<Player> players, Formation formation) {
        this.round = round;
        this.userId = userId;
        this.players = players;
        this.formation = formation;
    }


    public long score(){
        long points = 0;
        for(Player player : players){
            points += player.scores.stream().filter(score -> score.round == this.round).collect(summarizingInt(score -> score.points)).getSum();
        }
        return points;
    }

    public boolean addPlayer(Player player){
        if(validateFormation(player.position)){
            players.add(player);
            return true;
        }
        return false;
    }

    private Boolean validateFormation(Position position) {

        long currentCount = players.stream().filter(current -> current.position.equals(position)).count();
        switch(position){
            case GOALKEEPER:
                return (currentCount < formation.goalkeepers);
            case DEFENDER:
                return (currentCount < formation.defenders);
            case MIDFIELD:
                return currentCount < formation.midfielders;
            case FORWARD:
                return (currentCount < formation.forwards) ;
            case SUBSTITUTE:
                return (currentCount < formation.substitutes);
            default:
                throw new IllegalArgumentException("Position " + position.name() + " is not valid");
        }
    }
}
