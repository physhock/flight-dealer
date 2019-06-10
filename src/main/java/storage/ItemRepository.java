package storage;

import businesslogic.Item;
import dao.ItemMapper;

import java.sql.SQLException;

public class ItemRepository extends Repository {

    private static ItemRepository instance;
    private ItemMapper itemMapper;

    public ItemRepository(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public void addItem(Item item) {
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


}
