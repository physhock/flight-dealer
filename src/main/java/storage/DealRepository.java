package storage;

import businesslogic.Deal;
import org.hibernate.Session;

import java.util.ArrayList;

public class DealRepository extends Repository {
    private static ArrayList<Deal> deals;

    public DealRepository(Session session, ArrayList<Deal> deals) {
        super(session);
        DealRepository.deals = deals;
    }

    public void placeDeal(Deal deal) {
        deals.add(deal);
    }

    public static ArrayList<Deal> getDeals() {
        return deals;
    }


}
