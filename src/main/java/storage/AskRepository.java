package storage;

import businesslogic.Ask;
import businesslogic.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class AskRepository extends Repository {

    private ArrayList<Ask> ask;

    public AskRepository(ArrayList<Ask> ask) {
        this.ask = ask;
    }

    public void placeAsk(Ask ask){
        this.ask.add(ask);
    }

    public Ask searchLowestAsk(Item item){

        return this.ask.stream()
                .filter(x -> x.item.equals(item))
                .min(Comparator.comparing(Ask :: getAsk))
                .orElseThrow( () -> new NoSuchElementException(item.name + " not present yet"));

    }

}
