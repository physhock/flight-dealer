package businesslogic;

import user.Buyer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Order")
@Table(name = "orders")
public class Order extends AbstractDomain {
    @OneToOne
    private Item item;

    private String trackingId;

    @ManyToOne
    private Buyer buyer;

    public Order(){

    }

    public Order(Item item, String trackingId, Buyer buyer) {
        this.item = item;
        this.trackingId = trackingId;
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public Item getItem() {
        return item;
    }

    public String getTrackingId() {
        return trackingId;
    }
}
