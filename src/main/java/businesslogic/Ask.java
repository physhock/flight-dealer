package businesslogic;

import user.Seller;

public class Ask {

    public Item item;
    public int ask;
    private Seller seller;

    public Ask(Seller seller, Item item, int ask) {
        this.seller = seller;
        this.item = item;
        this.ask = ask;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getAsk() {
        return ask;
    }
}
