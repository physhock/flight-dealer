package user;

import businesslogic.Deal;
import businesslogic.Order;
import storage.DealRepository;
import storage.OrderRepository;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Administrator")
@Table(name = "administrators")
public class Administrator extends User {

    @OneToMany
    private List<Deal> deals = new ArrayList<>();

    public Administrator(ArrayList<Deal> deals) {
        this.deals = deals;
    }

    public Administrator(){

    }

    public Administrator(String userName, String password, UserStatus userStatus, ArrayList<Deal> deals) {
        super(userName, password, userStatus);
        this.deals = deals;
    }

    public Administrator(String userName, String password) {
        super(userName, password, UserStatus.ONLINE);
        this.deals = new ArrayList<>();
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(ArrayList<Deal> deals) {
        this.deals = deals;
    }

    private ArrayList<Deal> findDeals() {

        return DealRepository.getDeals().stream()
                .filter(deal -> deal.getAdministrator().equals(this) && deal.getDealStatus().equals(Deal.DealStatus.OPEN))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void makeDecision(Deal deal, Deal.DealStatus dealStatus) {

        deal.setDealStatus(dealStatus);

        if (dealStatus.equals(Deal.DealStatus.APPROVED)) {
            // payment operations ( money transfer to the seller )
            // and deliver item to the buyer
            Order order = new Order(deal.getItem(), "123SPBRU", deal.getBuyer());
            OrderRepository.placeOrder(order);
        }
    }

}
