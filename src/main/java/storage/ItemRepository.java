package storage;

import businesslogic.Item;
import dao.DB;
import dao.ItemMapper;

import java.sql.SQLException;

public class ItemRepository extends Repository {

    private static ItemRepository instance;
    private ItemMapper itemMapper;

    public ItemRepository(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public static ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepository(DB.itemMapper);
        }
        return instance;
    }

    public void addItem(Item item) {
        try {
            item.setId(itemMapper.insert(item));
        }
        catch (SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

//    public Item searchByNameAndSize(String name, int size) {
//        return items.stream().filter(item -> item.name.equals(name) && item.size == size)
//                .findFirst().orElseThrow(() -> new NoSuchElementException(name + "with size " + size + " not present yet"));
//    }


}
