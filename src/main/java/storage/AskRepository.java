package storage;

import businesslogic.Ask;
import businesslogic.Item;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class AskRepository extends Repository {

    private static ArrayList<Ask> asks;

    public AskRepository() {
        asks = new ArrayList<>();
    }

    public static ArrayList<Ask> getAsks() {
        Session session = newSession();
        session.beginTransaction();
        List<Ask> asks_raw = session.createQuery("from Ask", Ask.class).list();
        session.getTransaction().commit();
        session.close();
        asks.clear();
        asks.addAll(asks_raw);
        return asks;
    }

    public static void placeAsk(Ask ask) {
        Session session = newSession();
        session.beginTransaction();
        session.save(ask);
        session.getTransaction().commit();
        session.close();
        asks.add(ask);
    }

    public static void removeAsk(Ask ask) {
        Session session = newSession();
        session.beginTransaction();
        session.remove(ask);
        session.getTransaction().commit();
        session.close();
        asks.remove(ask);
    }

    public Ask searchLowestAsk(Item item) {

        return asks.stream()
                .filter(x -> x.getItem().equals(item))
                .min(Comparator.comparing(Ask::getAsk))
                .orElseThrow(() -> new NoSuchElementException(item.getName() + " not present yet"));

    }

}
