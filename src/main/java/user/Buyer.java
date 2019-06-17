package user;

import businesslogic.*;
import services.AssignService;
import storage.AskRepository;
import storage.BetRepository;
import storage.DealRepository;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name = "Buyer")
@Table(name = "buyers")
public class Buyer extends User {

    @OneToMany
    private List<Order> orders = new ArrayList<>();

    public Buyer(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Buyer() {
    }

    public Buyer(String userName, String password, UserStatus userStatus, ArrayList<Order> orders) {
        super(userName, password, userStatus);
        this.orders = orders;
    }

    public Buyer(String userName, String password) {
        super(userName, password, UserStatus.OFFLINE);
    }


    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
        //UserRepository.getOrders(this);
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void makeBet(Item item, int bet) {

        Bet newBet = new Bet(this, item, bet);
        BetRepository.placeBet(newBet);
        Optional<Ask> ask = checkForAsk(newBet);

        //AskRepository.removeAsk(ask.get());
        ask.ifPresent(value -> DealRepository.placeDeal(AssignService.assignAdministratorToDeal(new Deal(value, newBet))));
    }

    private Optional<Ask> checkForAsk(Bet bet) {

        return AskRepository.getAsks()
                .stream().filter(x -> x.getItem().equals(bet.getItem()) && x.getAsk() == bet.getBet())
                .findFirst();
    }

    public void closeOrder(String trackingId) {
        Order delivered = orders.stream().filter(order -> order.getTrackingId().equals(trackingId)).findFirst().get();
        orders.remove(delivered);
        DealRepository.getDeals().stream()
                .filter(deal -> deal.getItem().equals(delivered.getItem()) && deal.getBuyer().equals(this))
                .findFirst().get().setDealStatus(Deal.DealStatus.CLOSED);
    }


}
