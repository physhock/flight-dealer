package businesslogic;

import user.Seller;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Ask")
@Table(name = "asks")
public class Ask extends AbstractDomain {

    @OneToOne
    private Item item;

    private int ask;

    @ManyToOne
    private Seller seller;

    public Ask() {
    }

    public Ask(Item item, int ask, Seller seller) {
        this.item = item;
        this.ask = ask;
        this.seller = seller;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAsk() {
        return ask;
    }

    public void setAsk(int ask) {
        this.ask = ask;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
