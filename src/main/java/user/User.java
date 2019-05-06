package user;

import java.util.Objects;

public abstract class User {

    private String userName;
    private String password;
    public enum Status{
        ONLINE,
        OFFLINE
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    //Start session
    public int logIn(){

        if (this.userName.isEmpty() || this.password.isEmpty()){
            return -1;
        }

        return 0;
    }

    //End session
    public int logOut(){
        return 0;
    }


    public void searchItem(String name){

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
