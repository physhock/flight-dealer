import businesslogic.Ask;
import businesslogic.Bet;
import businesslogic.Deal;
import businesslogic.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.AskRepository;
import storage.BetRepository;
import storage.DealRepository;
import user.Administrator;
import user.Buyer;
import user.Seller;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


class MarketplaceTest {

    private Administrator administrator = new Administrator("adm", "321");
    private Buyer buyer = new Buyer("Dima", "123");
    private Seller seller = new Seller("Fedor", "123");
    private Item item = new Item("abibas", 42);

    private ArrayList<Ask> asks = AskRepository.getInstance().getAsks();
    private ArrayList<Bet> bets = BetRepository.getInstance().getBets();
    private ArrayList<Deal> deals = DealRepository.getInstance().getDeals();

    @BeforeEach
    public void logInUsers() {

        administrator.logIn();
        buyer.logIn();
        seller.logIn();

    }

    @Test
    public void testForBP() {

        // Insta-sell
        buyer.makeBet(item, 2500);
        seller.makeAsk(item, 2500);

        // Old ask & bet removed from lists
        assertTrue(asks.isEmpty() && bets.isEmpty());

        // Check deal creation
        assertTrue(deals.stream().anyMatch(deal -> deal.getBuyer().equals(buyer) && deal.getSeller().equals(seller) && deal.getAdministrator().equals(administrator)));

        // Approve deal by admin, and send item to the buyer
        administrator.makeDecision(DealRepository.getInstance().getDeals()
                .stream().filter(deal1 -> deal1.getAdministrator().equals(administrator)).findFirst().get(), Deal.DealStatus.APPROVED);

        // Check for the item in order list
        assertTrue(buyer.getOrders().stream().anyMatch(order -> order.getItem().equals(item)));

        // Order delivered to the buyer
        buyer.closeOrder(buyer.getOrders().stream().filter(order -> order.getItem().equals(item)).findFirst().get().getTrackingId());

        // Check for the deal status
        assertTrue(deals.stream().anyMatch(deal -> deal.getBuyer().equals(buyer)
                && deal.getSeller().equals(seller)
                && deal.getAdministrator().equals(administrator)
                && deal.getDealStatus().equals(Deal.DealStatus.CLOSED)));

        logOutUsers();
    }

    @Test
    public void instantSell() {
        // Insta-sell
        buyer.makeBet(item, 2500);
        seller.makeAsk(item, 2500);

        // Check deal creation
        assertTrue(deals.stream().anyMatch(deal -> deal.getBuyer().equals(buyer) && deal.getSeller().equals(seller) && deal.getAdministrator().equals(administrator)));

    }

    @Test
    public void placeAsk() {
        // Insta-sell
        seller.makeAsk(item, 2500);

        // Check if ask placed
        assertTrue(asks.stream().anyMatch(ask -> ask.getSeller().equals(seller)));
    }

    @Test
    public void approveDeal() {

        // Insta-sell
        buyer.makeBet(item, 2500);
        seller.makeAsk(item, 2500);

        // Approve deal by admin, and send item to the buyer
        administrator.makeDecision(DealRepository.getInstance().getDeals()
                .stream().filter(deal1 -> deal1.getAdministrator().equals(administrator)).findFirst().get(), Deal.DealStatus.APPROVED);


        // Check for the item in order list
        assertTrue(buyer.getOrders().stream().anyMatch(order -> order.getItem().equals(item)));
    }

    @Test
    public void instantBuy() {
        // Insta-buy
        seller.makeAsk(item, 2500);
        buyer.makeBet(item, 2500);

        // Check deal creation
        assertTrue(deals.stream().anyMatch(deal -> deal.getBuyer().equals(buyer) && deal.getSeller().equals(seller) && deal.getAdministrator().equals(administrator)));

    }

    @Test
    public void placeBet() {
        // Insta-sell
        buyer.makeBet(item, 2500);

        // Check if ask placed
        assertTrue(bets.stream().anyMatch(ask -> ask.getBuyer().equals(buyer)));
    }

    @AfterEach
    public void logOutUsers() {

        administrator.logOut();
        buyer.logOut();
        seller.logOut();

    }

}