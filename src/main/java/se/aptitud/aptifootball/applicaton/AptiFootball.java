package se.aptitud.aptifootball.applicaton;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import se.aptitud.aptifootball.league.LeagueResource;
import se.aptitud.aptifootball.player.PlayerResource;
import se.aptitud.aptifootball.user.UserResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;


public class AptiFootball extends Application<AptiFootballConfig> {

    public static void main(String[] args) throws Exception {
        new AptiFootball().run(args);
    }

    @Override
    public void run(AptiFootballConfig aptiFootballConfig, Environment environment) throws Exception {

        environment.jersey().register(new UserResource(aptiFootballConfig));
        environment.jersey().register(new PlayerResource(aptiFootballConfig));
        environment.jersey().register(new LeagueResource(aptiFootballConfig));

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "http://localhost:63343, http://aptifootball-api.herokuapp.com/index.html ");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");


    }


    public void initialize(Bootstrap<AptiFootballConfig> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }
}
