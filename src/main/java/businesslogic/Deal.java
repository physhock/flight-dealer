package businesslogic;

import storage.DealRepository;
import storage.UserRepository;
import user.Buyer;
import user.Seller;
import user.User;

public class Deal {

    private Ask ask;
    private Bet bet;
    private User administrator;
    private DealStatus dealStatus;

    public Deal(Ask ask, Bet bet) {
        this.ask = ask;
        this.bet = bet;
        this.setDealStatus(dealStatus.OPEN);
        this.administrator = UserRepository.getInstance().getUsers().stream()
                .filter(x -> x.getClass().getSimpleName().equals("Administrator") && x.getUserStatus().equals(User.UserStatus.ONLINE))
                .findFirst()
                .orElse(UserRepository.getInstance().getUsers().stream().filter(user -> user.getClass().getSimpleName().equals("Administrator")).findAny().get());
        DealRepository.getInstance().placeDeal(this);

    }

    public Buyer getBuyer() {
        return bet.getBuyer();
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public Seller getSeller() {
        return ask.getSeller();
    }

    public Item getItem() {
        return ask.item;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public User getAdministrator() {
        return administrator;
    }

    public enum DealStatus {
        OPEN,
        APPROVED,
        CLOSED
    }
}
