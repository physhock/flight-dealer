package user;

import businesslogic.Ask;
import businesslogic.Bet;
import businesslogic.Deal;
import businesslogic.Item;
import storage.AskRepository;
import storage.BetRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Seller extends User {
    private ArrayList<Ask> asks;

    public Seller(String userName, String password) {
        super(userName, password);
    }

    public ArrayList<Ask> getAsks() {
        return asks;
    }

    public void searchAsks() {
        asks = AskRepository.getInstance().getAsks().stream()
                .filter(ask -> ask.getSeller().getUserName().equals(this.getUserName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void makeAsk(Item item, int ask) {
        Ask newAsk = new Ask(this, item, ask);

        Optional<Bet> bet = checkForBet(newAsk);

        if (bet.isPresent()) {
            new Deal(newAsk, bet.get());
            BetRepository.getInstance().removeBet(bet.get());
        } else
            AskRepository.getInstance().placeAsk(newAsk);

    }

    private Optional<Bet> checkForBet(Ask ask) {

        return BetRepository.getInstance().getBets()
                .stream().filter(x -> x.item.equals(ask.item) && x.bet == ask.ask)
                .findFirst();

    }

}
