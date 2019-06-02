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
        return "SELECT" + "COLUMNS" + "FROM item" + "WHERE id = ?";
    }

    private String insertStatement() {
        return "INSERT INTO item (item_name,size) VALUES (?, ?) RETURNING item_id";
    }

    public Item find(Long id) throws SQLException {

        //`size` not actually loadedMap key!
        // but item_id actually is!
        Item result = loadedMap.get(id);

        if (result != null)
            return result;
        PreparedStatement findStatement;
        try {
            findStatement = connection.prepareStatement(findStatement());
            findStatement.setLong(1, id);
            ResultSet resultSet = findStatement.executeQuery();
            resultSet.next();
            result = load(resultSet);
            return result;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    private Item load(ResultSet resultSet) throws SQLException {

        Long item_id = resultSet.getLong(1);
        if (loadedMap.containsKey(item_id))
            return loadedMap.get(item_id);
        Item result = doLoad(resultSet);
        loadedMap.put(item_id, result);
        return result;
    }


    private Item doLoad(ResultSet resultSet) throws SQLException {

        String item_name = resultSet.getString(2);
        String size = resultSet.getString(3);
        // Loosing precision here!!!
        return new Item(item_name, Integer.getInteger(size));

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

        insertStatement.setString(1, item.name);
        insertStatement.setObject(2, item.size, Types.OTHER);
    }
}
