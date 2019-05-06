package user;

public class Administrator extends User{

    public Administrator(String userName, String password) {
        super(userName, password);
    }

    public boolean aprooveDeal(int dealId){ return true;}

    public boolean rejectDeal(int dealId) {return false;}


}
