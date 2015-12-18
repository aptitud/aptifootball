package se.aptitud.aptifootball.user;

import cucumber.api.java.Before;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import se.aptitud.aptifootball.team.Team;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserRepoTest {

     static UserRepo repo;


    @Ignore
    @Test
    public void addingUserShouldStore(){
        repo = new UserRepo("dummy", "dummy", "mongodb://192.168.59.103");
        User user = new User(null, "uname", "je@email.com", emptyList());
        repo.addUser(user);
        List<User> users = repo.listUsers();
        assertThat(users.size() > 1, is(true));

    }

}