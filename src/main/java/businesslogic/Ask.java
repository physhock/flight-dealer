package businesslogic;

import user.Seller;

public class Ask {

    private Seller seller;
    public Item item;

    public int ask;

    public Ask(Seller seller, Item item, int ask) {
        this.seller = seller;
        this.item = item;
        this.ask = ask;
    }



    public int getAsk() {
        return ask;
    }
}
