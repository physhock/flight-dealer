package user;

import storage.UserRepository;

import java.util.Objects;
import java.util.Random;


public abstract class User {

    private Long id;
    private String userName;
    private String password;
    private UserStatus userStatus;

    public User(String userName, String password) {
        // TODO
        // get next id from DB
        this.id = new Random().nextLong();
        this.userName = userName;
        this.password = password;
        UserRepository.getInstance().addUser(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserName() {
        return userName;
    }

    public int logIn() {

        if (this.userName.isEmpty() || this.password.isEmpty()) {
            return -1;
        }

        setUserStatus(UserStatus.ONLINE);
        return 0;
    }

    public int logOut() {

        setUserStatus(UserStatus.OFFLINE);
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    public enum UserStatus {
        ONLINE,
        OFFLINE
    }
}
