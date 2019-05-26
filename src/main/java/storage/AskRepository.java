package storage;

import businesslogic.Ask;
import businesslogic.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class AskRepository extends Repository {

    private static AskRepository instance;
    private ArrayList<Ask> asks;

    public AskRepository(ArrayList<Ask> ask) {
        this.asks = ask;
    }

    public static AskRepository getInstance() {
        if (instance == null) {
            instance = new AskRepository(new ArrayList<>());
        }
        return instance;
    }

    public ArrayList<Ask> getAsks() {
        return asks;
    }

    public void placeAsk(Ask ask) {
        this.asks.add(ask);
    }

    public void removeAsk(Ask ask) {
        asks.remove(ask);
    }

    public Ask searchLowestAsk(Item item) {

        return this.asks.stream()
                .filter(x -> x.item.equals(item))
                .min(Comparator.comparing(Ask::getAsk))
                .orElseThrow(() -> new NoSuchElementException(item.name + " not present yet"));

    }

}
