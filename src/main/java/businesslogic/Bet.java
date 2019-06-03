package businesslogic;

import user.Buyer;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Bet")
@Table(name = "bets")
public class Bet extends AbstractDomain {
    @OneToOne
    private Item item;

    private int bet;

    @OneToOne
    private Buyer buyer;

    public Bet() {
    }

    public Bet(Buyer buyer, Item item, int bet) {
        this.buyer = buyer;
        this.item = item;
        this.bet = bet;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}
