package businesslogic;

import user.Administrator;
import user.Buyer;
import user.Seller;
import user.User;

import javax.persistence.*;

@Entity(name = "Deal")
@Table(name = "deals")
public class Deal extends AbstractDomain {

    @OneToOne
    private Ask ask;

    @OneToOne
    private Bet bet;

    @OneToOne
    private Administrator administrator;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "deal_status")
    private DealStatus dealStatus;

    public Deal(Ask ask, Bet bet, Administrator administrator, DealStatus dealStatus) {
        this.ask = ask;
        this.bet = bet;
        this.administrator = administrator;
        this.dealStatus = dealStatus;
    }

    public Deal(Ask ask, Bet bet) {
        this.ask = ask;
        this.bet = bet;
    }

    public Deal() {
    }

    public Ask getAsk() {
        return ask;
    }

    public void setAsk(Ask ask) {
        this.ask = ask;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public Buyer getBuyer() {
        return bet.getBuyer();
    }

    //TODO
    // 1. Update BP logic
    // 2. Add queries to repos
    // 3. FIX TESTS!
    // 4. Start implementing UI
    // 5. Report!!!

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Seller getSeller() {
        return ask.getSeller();
    }

    public Item getItem() {
        return ask.getItem();
    }

    public User getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
        this.setDealStatus(DealStatus.OPEN);
    }

    public enum DealStatus {
        OPEN,
        APPROVED,
        CLOSED
    }
}
