package businesslogic;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Item")
@Table(name = "items",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "size"})}
        )
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String size;

    public Item(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
