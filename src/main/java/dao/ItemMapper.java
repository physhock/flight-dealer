package dao;

import businesslogic.Ask;
import businesslogic.Item;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemMapper {

    public static final String COLUMNS = "id, name, size";
    private HashMap<Long, Item> loadedMap;
    private Connection connection;

    public ItemMapper(Connection connection) {
        this.loadedMap = new HashMap<>();
        this.connection = connection;
    }

    private String findStatement() {
        return "SELECT id FROM items WHERE name = ? AND size = ?";
    }

    private String insertStatement() {
        return "INSERT INTO items (name,size) VALUES (?, ?) RETURNING id";
    }

    private String selectStatement() {
        return "SELECT * FROM items";
    }

    private String selectWithAsksStatement() {
        return "SELECT items.id, items.name, items.size, min(a.ask)\n" +
                "FROM items\n" +
                "left outer join asks a on items.id = a.item_id\n" +
                "group by items.id;";
    }

    private String selectWithBetsStatement() {
        return "SELECT items.id, items.name, items.size, max(b.bet)\n" +
                "FROM items\n" +
                "left outer join bets b on items.id = b.item_id\n" +
                "group by items.id;";
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
            findStatement.setString(2, size);
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

    public ArrayList<Item> getAll() throws SQLException {
        PreparedStatement selectStatement;
        try {
            selectStatement = connection.prepareStatement(selectStatement());
            ResultSet resultSet = selectStatement.executeQuery();
            ArrayList <Item> items = new ArrayList<>();
            while (resultSet.next()){
                Item item = new Item(resultSet.getString(2), resultSet.getString(3));
                item.setId(resultSet.getLong(1));
                items.add(item);
            }
            return items;
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
        insertStatement.setString(2, item.getSize());
    }

    public ArrayList<Pair<Item, Integer>> getAllWithBets() throws SQLException{
        PreparedStatement selectWithAsksStatement;
        try {
            selectWithAsksStatement = connection.prepareStatement(selectWithBetsStatement());
            return getPairs(selectWithAsksStatement);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ArrayList<Pair<Item, Integer>> getAllWithAsks() throws SQLException{
        PreparedStatement selectWithAsksStatement;
        try {
            selectWithAsksStatement = connection.prepareStatement(selectWithAsksStatement());
            return getPairs(selectWithAsksStatement);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private ArrayList<Pair<Item, Integer>> getPairs(PreparedStatement selectWithAsksStatement) throws SQLException {
        ResultSet resultSet = selectWithAsksStatement.executeQuery();
        ArrayList<Pair<Item, Integer>> items = new ArrayList<>();
        while (resultSet.next()){
            Item item = new Item(resultSet.getString(2), resultSet.getString(3));
            item.setId(resultSet.getLong(1));
            items.add(new Pair<>(item, resultSet.getInt(4)));
        }
        return items;
    }
}
