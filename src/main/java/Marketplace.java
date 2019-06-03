import utills.DB;

public class Marketplace {

    public static void main(String[] args) {
        System.out.println("marketplace started");

        DB database = new DB();
        database.startDB();

    }

}
