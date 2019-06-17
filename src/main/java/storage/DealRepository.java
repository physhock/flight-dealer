package storage;

import businesslogic.Deal;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DealRepository extends Repository {

    private static ArrayList<Deal> deals;

    public DealRepository() {
        deals = new ArrayList<>();
    }

    public static void placeDeal(Deal deal) {
        Session session = newSession();
        session.beginTransaction();
        session.save(deal);
        session.getTransaction().commit();
        deals.add(deal);
    }

    public static ArrayList<Deal> getDeals() {
        Session session = newSession();
        session.beginTransaction();
        List<Deal> deals = session.createQuery("from Deal", Deal.class).list();
        session.getTransaction().commit();
        DealRepository.deals.addAll(deals);
        return DealRepository.deals;
    }

    public static void updateDeal(Deal deal) {
        Session session = newSession();
        session.beginTransaction();
        session.update(deal);
        session.getTransaction().commit();
    }


}
