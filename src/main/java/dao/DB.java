package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    public static ItemMapper itemMapper;

    public static void startDB() {
        String url = "jdbc:postgresql://localhost:5432/marketplace";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("ssl", "false");
        try{
            Connection connection = DriverManager.getConnection(url, props);
            System.out.println("DB connected");
            itemMapper = new ItemMapper(connection);

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

}
