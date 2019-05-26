package businesslogic;

public class Order {
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
