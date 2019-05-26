package businesslogic;

import storage.UserRepository;
import user.User;

public class Deal {

    private Ask ask;
    private Bet bet;
    private User administrator;
    private DealStatus dealStatus;

    public enum DealStatus {
        OPEN,
        CLOSED
    }

    public Deal(Ask ask, Bet bet) {
        this.ask = ask;
        this.bet = bet;
        this.setDealStatus(dealStatus.OPEN);
        this.administrator = UserRepository.getInstance().getUsers().stream()
                .filter(x -> x.getClass().getCanonicalName().equals("Administrator") && x.getUserStatus().equals(User.UserStatus.ONLINE)).findFirst().get();

    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }
}
