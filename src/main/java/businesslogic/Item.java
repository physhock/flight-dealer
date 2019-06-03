package businesslogic;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "Item")
@Table(name = "items")
public class Item extends AbstractDomain {

    private String name;
    private String size;

    public Item(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return size.equals(item.size) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
