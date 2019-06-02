import businesslogic.Ask;
import businesslogic.Deal;
import businesslogic.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.AskRepository;
import storage.DealRepository;
import user.Administrator;
import user.Buyer;
import user.Seller;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class MarketplaceTest {

    private Administrator administrator = new Administrator("adm", "321");
    private Buyer buyer = new Buyer("Dima", "123");
    private Seller seller = new Seller("Fedor", "123");
    private Item item = new Item("abibas", 42);

    private ArrayList<Ask> asks = AskRepository.getInstance().getAsks();
    private ArrayList<Deal> deals = DealRepository.getInstance().getDeals();


    public void logInUsers() {

        administrator.logIn();
        buyer.logIn();
        seller.logIn();

    }

    public void logOutUsers() {

        administrator.logOut();
        buyer.logOut();
        seller.logOut();

    }

    @BeforeEach
    public void dataLoad() {
        logInUsers();
        buyer.makeBet(item, 2500);
        seller.makeAsk(item, 2500);

        administrator.makeDecision(DealRepository.getInstance().getDeals()
                .stream().filter(deal1 -> deal1.getAdministrator().equals(administrator)).findFirst().get(), Deal.DealStatus.APPROVED);

    }


    @Test
    public void checkAsk() {

        assertEquals(item, asks.get(0).item);
        assertTrue(asks.stream().anyMatch(ask -> ask.getSeller().equals(seller)));
    }


    @Test
    public void checkBet() {

        //ArrayList<Deal> deals = DealRepository.getInstance().getDeals();

        assertTrue(deals.stream().anyMatch(deal -> deal.getBuyer().equals(buyer)
                && deal.getAdministrator().equals(administrator)
                && deal.getSeller().equals(seller)));
    }

    @Test
    public void checkDeal() {

        assertFalse(buyer.getOrders().isEmpty());

    }


    @Test
    public void closeDeal() {


        buyer.closeOrder(buyer.getOrders().get(0).getTrackingId());

        logOutUsers();

        ArrayList<Deal> deals = DealRepository.getInstance().getDeals();

        assertTrue(deals.stream().filter(deal -> deal.getBuyer().equals(buyer)).allMatch(deal -> deal.getDealStatus().equals(Deal.DealStatus.CLOSED)));
    }

}