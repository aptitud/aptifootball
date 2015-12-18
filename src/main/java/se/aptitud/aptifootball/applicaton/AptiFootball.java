package se.aptitud.aptifootball.applicaton;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import se.aptitud.aptifootball.player.PlayerResource;
import se.aptitud.aptifootball.user.UserResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import java.util.EnumSet;

public class AptiFootball extends Application<AptiFootballConfig> {

    public static void main(String[] args) throws Exception {
        new AptiFootball().run(args);
    }

    @Override
    public void run(AptiFootballConfig aptiFootballConfig, Environment environment) throws Exception {
        environment.jersey().register(new UserResource(aptiFootballConfig));
        environment.jersey().register(new PlayerResource(aptiFootballConfig));

        final Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }


    public void initialize(Bootstrap<AptiFootballConfig> bootstrap) {
        bootstrap.addBundle(new ViewBundle<AptiFootballConfig>());
    }
}
