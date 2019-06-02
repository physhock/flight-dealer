package storage;

import user.User;

import java.util.ArrayList;

public class UserRepository extends Repository {

    private static UserRepository instance;
    private ArrayList<User> users;

    public UserRepository(ArrayList<User> users) {
        this.users = users;
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository(new ArrayList<>());
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
