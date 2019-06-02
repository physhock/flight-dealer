package businesslogic;

import storage.ItemRepository;

import java.util.Objects;

public class Item extends AbstractDomain {

    public String name;
    public int size;

    public Item(String name, int size) {
        this.name = name;
        this.size = size;
        ItemRepository.getInstance().addItem(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return size == item.size &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
