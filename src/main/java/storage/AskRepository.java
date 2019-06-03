package storage;

import businesslogic.Ask;
import businesslogic.Item;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class AskRepository extends Repository {

    private static ArrayList<Ask> asks;

    public AskRepository(Session session, ArrayList<Ask> asks) {
        super(session);
        AskRepository.asks = asks;
    }

    public static ArrayList<Ask> getAsks() {
        return asks;
    }

    public void placeAsk(Ask ask) {
        asks.add(ask);
    }

    public void removeAsk(Ask ask) {
        asks.remove(ask);
    }

    public Ask searchLowestAsk(Item item) {

        return asks.stream()
                .filter(x -> x.getItem().equals(item))
                .min(Comparator.comparing(Ask::getAsk))
                .orElseThrow(() -> new NoSuchElementException(item.getName() + " not present yet"));

    }

}
