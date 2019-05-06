import businesslogic.Ask;
import businesslogic.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.AskRepository;
import user.Administrator;
import user.Buyer;
import user.Seller;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import  org.jeasy.random.*;

import static org.junit.jupiter.api.Assertions.*;


class MarketplaceTest {

    private Administrator administrator = new Administrator("adm", "321");
    private Buyer buyer = new Buyer("Dima", "123");
    private Seller seller = new Seller("Fedor", "123");

    private AskRepository askRepository;
    private ArrayList<Ask> asks;
    private EasyRandom easyRandom = new EasyRandom();

    @BeforeEach
    public void makeAsk(){
        Item item = new Item("abibas", 10);
        Ask ask = new Ask(seller, item, 10000);
        asks = easyRandom.objects(Ask.class, 10).collect(Collectors.toCollection(ArrayList::new));
        askRepository = new AskRepository(asks);
        askRepository.placeAsk(ask);
    }

    @Test
    public void notFoundItem(){
        Item lost = new Item("barcetka", 20);
        assertThrows(NoSuchElementException.class, () -> askRepository.searchLowestAsk(lost));
    }

    @Test
    public void foundAsk(){
        assertEquals(10000,
                askRepository.searchLowestAsk(new Item("abibas", 10)).ask);
    }


}