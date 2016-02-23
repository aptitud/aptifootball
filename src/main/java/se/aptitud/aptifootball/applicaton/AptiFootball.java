package se.aptitud.aptifootball.applicaton;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import se.aptitud.aptifootball.league.LeagueResource;
import se.aptitud.aptifootball.player.PlayerResource;
import se.aptitud.aptifootball.user.UserResource;


public class AptiFootball extends Application<AptiFootballConfig> {

    public static void main(String[] args) throws Exception {
        new AptiFootball().run(args);
    }

    @Override
    public void run(AptiFootballConfig aptiFootballConfig, Environment environment) throws Exception {

        environment.jersey().register(new UserResource(aptiFootballConfig));
        environment.jersey().register(new PlayerResource(aptiFootballConfig));
        environment.jersey().register(new LeagueResource(aptiFootballConfig));

    }


    public void initialize(Bootstrap<AptiFootballConfig> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }
}
