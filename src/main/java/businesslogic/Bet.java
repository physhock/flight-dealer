package businesslogic;

import user.Buyer;

public class Bet {

    private Buyer buyer;
    public Item item;

    public int bet;

    public Bet(Buyer buyer, Item item, int bet) {
        this.buyer = buyer;
        this.item = item;
        this.bet = bet;
    }
}
