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

    private void searchAsks() {
        asks = AskRepository.getInstance().getAsks().stream()
                .filter(ask -> ask.getSeller().getUserName().equals(this.getUserName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<Deal> makeAsk(Item item, int ask) {
        Ask newAsk = new Ask(this, item, ask);

        Optional<Bet> bet = checkForBet(newAsk);

        if (bet.isPresent())
            return Optional.of(new Deal(newAsk, bet.get()));

        AskRepository.getInstance().placeAsk(newAsk);

        return Optional.empty();

    }


    private Optional<Bet> checkForBet(Ask ask) {

        return BetRepository.getInstance().getBets()
                .stream().filter(x -> x.item.equals(ask.item) && x.bet == ask.ask)
                .findFirst();

    }

}
