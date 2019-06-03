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
import java.util.Optional;

@Entity(name = "User")
@Table(name = "users")
public class Buyer extends User {

    @OneToMany
    private ArrayList<Order> orders = new ArrayList<>();

    public Buyer(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Buyer() {
    }

    public Buyer(String userName, String password, UserStatus userStatus, ArrayList<Order> orders) {
        super(userName, password, userStatus);
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

//    public void makeBet(Item item, int bet) {
//
//        Bet newBet = new Bet(this, item, bet);
//        Optional<Ask> ask = checkForAsk(newBet);
//
//        if (ask.isPresent()) {
//            DealRepository.getInstance()
//                    .placeDeal(AssignService.assignAdministratorToDeal(new Deal(ask.get(), newBet)));
//            AskRepository.getInstance().removeAsk(ask.get());
//        } else
//            BetRepository.getInstance().placeBet(newBet);
//
//    }

    public void closeOrder(String trackingId) {
        Order delivered = orders.stream().filter(order -> order.getTrackingId().equals(trackingId)).findFirst().get();
        orders.remove(delivered);
        DealRepository.getInstance().getDeals().stream()
                .filter(deal -> deal.getItem().equals(delivered.getItem()) && deal.getBuyer().equals(this))
                .findFirst().get().setDealStatus(Deal.DealStatus.CLOSED);
    }

    private Optional<Ask> checkForAsk(Bet bet) {

        return AskRepository.getInstance().getAsks()
                .stream().filter(x -> x.getItem().equals(bet.getItem()) && x.getAsk() == bet.getBet())
                .findFirst();
    }

}
