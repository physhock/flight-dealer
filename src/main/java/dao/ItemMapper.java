package dao;

import businesslogic.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return "INSERT INTO item VALUES (? ? ?)";
    }

    public Item find(Long item_id) throws SQLException {

        //`size` not actually loadedMap key!
        // but item_id actually is!
        Item result = loadedMap.get(item_id);

        if (result != null)
            return result;
        PreparedStatement findStatement;
        try {
            findStatement = connection.prepareStatement(findStatement());
            findStatement.setLong(1, item_id);
            ResultSet resultSet = findStatement.executeQuery();
            resultSet.next();
            result = load(resultSet);
            return result;
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            //cleanUp
        }
    }


    public Item load(ResultSet resultSet) throws SQLException {

        Long item_id = resultSet.getLong(1);
        if (loadedMap.containsKey(item_id))
            return loadedMap.get(item_id);
        Item result = doLoad(item_id, resultSet);
        loadedMap.put(item_id, result);
        return result;
    }


    public Item doLoad(Long itemId, ResultSet resultSet) throws SQLException {

        String item_name = resultSet.getString(2);
        String size = resultSet.getString(3);
        // Loosing precision here!!!
        return new Item(item_name, Integer.getInteger(size));

    }

    public Long insert(Item item) throws SQLException {
        PreparedStatement insertStatement = null;
        try {
            insertStatement = connection.prepareStatement(insertStatement());
            //id??
            Long item_id = findNextDatabaseId();
            insertStatement.setInt(1, item_id.intValue());
            doInsert(item, insertStatement);
            insertStatement.execute();
            loadedMap.put(item_id, item);
            return item_id;
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            //cleanUp
        }

    }

    public void doInsert(Item item, PreparedStatement insertStatement) throws SQLException {

        insertStatement.setString(2, item.name);
        insertStatement.setString(3, String.valueOf(item.size));
    }

    public Long findNextDatabaseId() {
        return new Long(1);
    }


}
