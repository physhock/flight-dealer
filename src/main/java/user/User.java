package user;

public abstract class User {

    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //Start session
    public int logIn(){

        if (this.userName.isEmpty() || this.password.isEmpty())
            return -1;
        return 0;
    }

    //End session
    public int logOut(){
        return 0;
    }


    public void searchItem(String name){

    }


}
