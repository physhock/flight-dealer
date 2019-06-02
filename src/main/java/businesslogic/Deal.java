package businesslogic;

import user.Buyer;
import user.Seller;
import user.User;

public class Deal extends AbstractDomain {

    private Ask ask;
    private Bet bet;
    private User administrator;
    private DealStatus dealStatus;

    public Deal(Ask ask, Bet bet) {
        this.ask = ask;
        this.bet = bet;
    }

    public Buyer getBuyer() {
        return bet.getBuyer();
    }

    //TODO
    // 3. UI last task, screen shot from Michael
    // 4. FIX tests, one big for BP, and many little tests for the use-cases

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
        return ask.item;
    }

    public User getAdministrator() {
        return administrator;
    }

    public void setAdministrator(User administrator) {
        this.administrator = administrator;
        this.setDealStatus(DealStatus.OPEN);
    }

    public enum DealStatus {
        OPEN,
        APPROVED,
        CLOSED
    }
}
