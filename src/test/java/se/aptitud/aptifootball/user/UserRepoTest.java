package se.aptitud.aptifootball.user;

import cucumber.api.java.Before;
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


    @BeforeClass
         public static void setUp(){
        repo = new UserRepo("dummy", "dummy");
    }


    @AfterClass
    public static void tearDown(){
        repo.cleanUp();
    }


    @Test
    public void addingUserShouldStore(){
        User user = new User(1, "uname", "je@email.com", emptyList());
        repo.addUser(user);
        List<User> users = repo.listUsers();
        assertThat(users.size(), is(1));
        assertThat(users, hasItem(user));

    }

}