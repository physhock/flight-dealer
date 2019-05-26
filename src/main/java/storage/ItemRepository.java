package storage;

import businesslogic.Item;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ItemRepository extends Repository {

    private static ItemRepository instance;
    private ArrayList<Item> items;

    public ItemRepository(ArrayList<Item> items) {
        this.items = items;
    }

    public static ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepository(new ArrayList<>());
        }
        return instance;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public Item searchByNameAndSize(String name, int size){
        return items.stream().filter(item -> item.name.equals(name) && item.size == size)
                .findFirst().orElseThrow( () -> new NoSuchElementException(name + "with size " + size +" not present yet"));
    }


}
