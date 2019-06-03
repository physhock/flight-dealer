package storage;

import org.hibernate.Session;
import user.User;

import java.util.ArrayList;

public class UserRepository extends Repository {

    private static ArrayList<User> users;

    public UserRepository(Session session, ArrayList<User> users) {
        super(session);
        UserRepository.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

}
