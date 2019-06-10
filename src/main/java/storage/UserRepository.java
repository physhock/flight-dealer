package storage;

import org.hibernate.Session;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository {

    private static ArrayList<User> users = new ArrayList<>();

    public UserRepository() {
        UserRepository.users = new ArrayList<>();
    }

    public static ArrayList<User> getUsers(String userRole) {
        Session session = newSession();
        session.beginTransaction();
        List<User> user_raw = session.createQuery("from " + userRole, User.class).list();
        session.getTransaction().commit();
        users.addAll(user_raw);

        return users;
    }

    public static void addUser(User user) {
        Session session = newSession();
        session.beginTransaction();
        session.save(user.getClass().getSimpleName(), user);
        session.getTransaction().commit();
        users.add(user);
    }


    public static void updateUser(User user) {
        Session session = newSession();
        boolean is = session.contains(user);
        session.beginTransaction();
        session.saveOrUpdate(user.getClass().getSimpleName(), user);
        session.getTransaction().commit();
        users.add(user);
    }
}
