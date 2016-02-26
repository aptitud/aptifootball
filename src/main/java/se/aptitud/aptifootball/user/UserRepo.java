package se.aptitud.aptifootball.user;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.aptitud.aptifootball.AptiFootballRepo;
import se.aptitud.aptifootball.applicaton.AptiFootballConfig;

import java.util.*;

public class UserRepo extends AptiFootballRepo {

    private final Logger log = LoggerFactory.getLogger(UserRepo.class);

    public UserRepo(AptiFootballConfig config) {
        super(config);
    }

    public User addUser(User user){
        Document doc = new Document("email", user.email)
                .append("type", "user")
                .append("userName", user.username);
        aptifootball.insertOne(doc);
        FindIterable<Document> documents = aptifootball.find(doc);
        Document storedUser = documents.iterator().next();
        return createUserObject(storedUser);
    }

    public List<User> listUsers() {
        Document doc = new Document("type", "user");
        FindIterable<Document> documents = aptifootball.find(doc);
        List<User> users = new ArrayList<>();
        documents.spliterator().forEachRemaining( user -> users.add(createUserObject(user)));
        log.debug("users :" + users );
        return users;
    }

    private User createUserObject(Document mongoUser) {
        return new User(mongoUser.get("_id").toString(), mongoUser.get("userName").toString(), mongoUser.get("email").toString(), null);
    }
}