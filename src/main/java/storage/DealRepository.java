package storage;

import businesslogic.Deal;
import org.hibernate.Session;

import java.util.ArrayList;

public class DealRepository extends Repository {

    private static ArrayList<Deal> deals = new ArrayList<>();

    public DealRepository() {
        deals = getDeals();
    }

    public static void placeDeal(Deal deal) {
        Session session = newSession();
        session.beginTransaction();
        session.save(deal);
        session.getTransaction().commit();
        deals.add(deal);
    }

    public static ArrayList<Deal> getDeals() {
        return deals;
    }


}
