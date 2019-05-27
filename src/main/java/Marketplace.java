import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Marketplace {

    public static void main(String[] args) {
        System.out.println("marketplace started");

        String url = "jdbc:postgresql://localhost:5432/marketplace";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("ssl", "false");
        try (Connection conn = DriverManager.getConnection(url, props)) {

            System.out.println("DB connected");

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }


    }

}
