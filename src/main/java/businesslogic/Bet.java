package businesslogic;

import user.Buyer;

public class Bet extends AbstractDomain {

    public Item item;
    public int bet;
    private Buyer buyer;

    public Bet(Buyer buyer, Item item, int bet) {
        this.buyer = buyer;
        this.item = item;
        this.bet = bet;
    }

    public Buyer getBuyer() {
        return buyer;
    }

}
