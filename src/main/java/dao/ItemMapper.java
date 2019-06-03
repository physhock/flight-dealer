package dao;

import businesslogic.Item;

import java.sql.*;
import java.util.HashMap;

public class ItemMapper {

    public static final String COLUMNS = "item_id, item_name, size";
    private HashMap<Long, Item> loadedMap;
    private Connection connection;

    public ItemMapper(Connection connection) {
        this.loadedMap = new HashMap<>();
        this.connection = connection;
    }

    private String findStatement() {
        return "SELECT item_id FROM item WHERE item_name = ? AND size = ?";
    }

    private String insertStatement() {
        return "INSERT INTO item (item_name,size) VALUES (?, ?) RETURNING item_id";
    }

    public Item find(String name, String size) throws SQLException {

        //`size` not actually loadedMap key!
        // but item_id actually is!
        Long item_id = loadedMap.entrySet().stream()
                .filter(item ->item.getValue().getName().equals(name) && item.getValue().getSize().equals(size)).findFirst().get().getKey();

        if (item_id != null)
            return loadedMap.get(item_id);
        PreparedStatement findStatement;
        try {
            findStatement = connection.prepareStatement(findStatement());
            findStatement.setString(1, name);
            findStatement.setObject(2, size, Types.OTHER);
            ResultSet resultSet = findStatement.executeQuery();
            resultSet.next();
            item_id = resultSet.getLong(1);
            Item item = new Item(name, size);
            item.setId(item_id);
            loadedMap.put(item_id, item);
            return item;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Long insert(Item item) throws SQLException {
        PreparedStatement insertStatement = null;
        try {
            insertStatement = connection.prepareStatement(insertStatement());
            doInsert(item, insertStatement);
            ResultSet item_id = insertStatement.executeQuery();
            item_id.next();
            loadedMap.put(item_id.getLong(1), item);
            return item_id.getLong(1);
        } catch (SQLException e) {
           throw new SQLException(e);
        }
    }

    private void doInsert(Item item, PreparedStatement insertStatement) throws SQLException {

        insertStatement.setString(1, item.getName());
        insertStatement.setObject(2, item.getSize(), Types.OTHER);
    }
}
