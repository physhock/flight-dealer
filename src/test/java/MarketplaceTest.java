import businesslogic.Deal;
import businesslogic.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import storage.*;
import user.Administrator;
import user.Buyer;
import user.Seller;
import utils.DB;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarketplaceTest {

    private static Administrator administrator;
    private static Buyer buyer;
    private static Seller seller;
    private static Item item;

    @BeforeAll
    public static void init(){

        DB db = new DB();
        db.startDB();

        administrator = new Administrator("Pavel", "123");
        buyer = new Buyer("Fedor", "123");
        seller = new Seller("Dima", "123");
        item = new Item("nike", "13,5");

        UserRepository.addUser(administrator);
        UserRepository.addUser(buyer);
        UserRepository.addUser(seller);
        ItemRepository.addItem(item);

    }


    @Test
    public void businessProcess() {

        assertFalse(UserRepository.getUsers(buyer.getClass().getSimpleName()).isEmpty());

        ItemRepository.addItem(item);

        buyer.makeBet(item, 1000);
        assertFalse(BetRepository.getBets().isEmpty());

        seller.makeAsk(item, 1000);
        //Deal created ( ask == bet)
        assertFalse(DealRepository.getDeals().isEmpty());

        Deal deal = DealRepository.getDeals().get(0);
        administrator.makeDecision(deal, Deal.DealStatus.APPROVED);

    }
    @Test
    public void makeBet(){

        buyer.makeBet(item, 1000);
        assertTrue(BetRepository.getBets().stream()
                .anyMatch(bet -> bet.getBuyer().equals(buyer)));
    }

    @Test
    public void makeAsk(){

        seller.makeAsk(item, 1000);
        assertTrue(AskRepository.getAsks().stream()
                .anyMatch(ask -> ask.getSeller().equals(seller)));

    }

    @Test
    public void makeDeal(){


        buyer.makeBet(item, 1000);
        seller.makeAsk(item, 1000);

        assertTrue(DealRepository.getDeals().stream()
                .anyMatch(deal -> deal.getSeller().equals(seller) && deal.getBuyer().equals(buyer)));

    }


}