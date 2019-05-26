package user;

import storage.UserRepository;

import java.util.Objects;


public abstract class User {

    private String userName;
    private String password;
    private UserStatus userStatus;

    public enum UserStatus {
        ONLINE,
        OFFLINE
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        UserRepository.getInstance().addUser(this);
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

    public String getPassword() {
        return password;
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
}