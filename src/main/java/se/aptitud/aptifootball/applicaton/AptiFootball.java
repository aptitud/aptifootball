package se.aptitud.aptifootball.applicaton;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import se.aptitud.aptifootball.player.PlayerResource;
import se.aptitud.aptifootball.user.UserResource;

public class AptiFootball extends Application<AptiFootballConfig> {

    public static void main(String[] args) throws Exception {
        new AptiFootball().run(args);
    }
    @Override
    public void run(AptiFootballConfig aptiFootballConfig, Environment environment) throws Exception {
            environment.jersey().register(new UserResource());
            environment.jersey().register(new PlayerResource(aptiFootballConfig));
    }


    public void initialize(Bootstrap<AptiFootballConfig> bootstrap) {
        bootstrap.addBundle(new ViewBundle<AptiFootballConfig>());
    }
}
