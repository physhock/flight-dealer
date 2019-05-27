package dao;

import businesslogic.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ItemMapper  {

    private Map map;
    private Connection connection;

    public ItemMapper(Connection connection) {
        this.map = new HashMap();
        this.connection = connection;
    }

    private String findStatement(){
        return "SELECT" + "COLUMNS" + "FROM item" + "WHERE size = ?";
    }

    public static final String COLUMNS = "item_id, item_name, size";

    public Item findBySize(String size){

        Item result = (Item) map.get(size);

        if (result != null)
            return result;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findStatement());
            preparedStatement.setString(1, size);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = load


        }catch (SQLException e) {

        }

    }


}
