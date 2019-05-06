package user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Administrator vasya = new Administrator("Vasiliy", "petych123");

    @Test
    void logIn() {

        assertEquals(0, vasya.logIn());
    }

    @Test
    void logOut() {
        assertEquals(0, vasya.logOut());
    }
}