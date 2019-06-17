package storage;

import businesslogic.Item;
import dao.ItemMapper;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemRepository extends Repository {

    private static ItemMapper itemMapper;

    public ItemRepository(ItemMapper itemMapper) {
        ItemRepository.itemMapper = itemMapper;
    }

    public static void addItem(Item item) {
        try {
            item.setId(itemMapper.insert(item));
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public Item searchByNameAndSize(String name, String size) {

        try {
            return itemMapper.find(name, size);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Item> getAllItems(){

        try {
            return itemMapper.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<Pair<Item, Integer>> getAllItemsWithLowestAsks(){

        try {
            return itemMapper.getAllWithAsks();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<Pair<Item, Integer>> getAllItemsWithHighestBet(){

        try {
            return itemMapper.getAllWithBets();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
