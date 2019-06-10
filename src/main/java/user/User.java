package user;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;
import storage.UserRepository;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String userName;

//    @Column(name = "pswd")
//    @ColumnTransformer(
//            read = "decrypt( 'AES', '00', pswd  )",
//            write = "encrypt('AES', '00', ?)"
//    )
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_status")
    private UserStatus userStatus;

    public User() {
    }

    public User(String userName, String password, UserStatus userStatus) {
        this.userName = userName;
        this.password = password;
        this.userStatus = userStatus;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int logIn() {

        if (this.userName.isEmpty() || this.password.isEmpty()) {
            return -1;
        }

        setUserStatus(UserStatus.ONLINE);
        UserRepository.updateUser(this);
        return 0;
    }

    public int logOut() {

        setUserStatus(UserStatus.OFFLINE);
        UserRepository.updateUser(this);
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
