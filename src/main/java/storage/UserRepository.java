package storage;

import user.User;

import java.util.ArrayList;

public class UserRepository extends Repository {

    private ArrayList<User> users;
    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository(new ArrayList<>());
        }
        return instance;
    }

    public UserRepository(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
