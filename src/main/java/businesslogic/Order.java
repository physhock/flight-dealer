package businesslogic;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Order")
@Table(name = "orders")
public class Order extends AbstractDomain {
    @OneToOne
    private Item item;

    private String trackingId;

    public Order(Item item, String trackingId) {
        this.item = item;
        this.trackingId = trackingId;
    }

    public Item getItem() {
        return item;
    }

    public String getTrackingId() {
        return trackingId;
    }
}
