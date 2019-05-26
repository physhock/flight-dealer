package user;

import businesslogic.Deal;
import storage.DealRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Administrator extends User {

    public Administrator(String userName, String password) {
        super(userName, password);
    }

    private ArrayList<Deal> deals;

    private void searchDeals() {
        deals = DealRepository.getInstance().getDeals().stream()
                .filter(deal -> deal.getAdministrator().getUserName().equals(this.getUserName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void makeDecision(Deal deal, Deal.DealStatus dealStatus){

        deal.setDealStatus(dealStatus);

    }

}
