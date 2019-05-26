package user;

import org.junit.jupiter.api.Test;
import storage.UserRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private ArrayList<User> users;
//    private UserRepository userRepository = new UserRepository(users);
    private Administrator vasya = new Administrator("Vasiliy", "petych123");

    @Test
    void logIn() {

        users = UserRepository.getInstance().getUsers();

        assertEquals(0, vasya.logIn());
    }

    @Test
    void logOut() {
        assertEquals(0, vasya.logOut());
    }
}