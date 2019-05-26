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

public class Buyer extends User {

    private ArrayList<Bet> bets;

    public Buyer(String userName, String password) {
        super(userName, password);
        searchBets();
    }

    private void searchBets() {
        bets = BetRepository.getInstance().getBets().stream()
                .filter(bet -> bet.getBuyer().getUserName().equals(this.getUserName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<Deal> makeBet(Item item, int bet) {

        Bet newBet = new Bet(this, item, bet);
        Optional<Ask> ask = checkForAsk(newBet);

        if (ask.isPresent())
            return Optional.of(new Deal(ask.get(), newBet));

        BetRepository.getInstance().placeBet(newBet);

        return Optional.empty();

    }

    private Optional<Ask> checkForAsk(Bet bet) {

        return AskRepository.getInstance().getAsks()
                .stream().filter(x -> x.item.equals(bet.item) && x.ask == bet.bet)
                .findFirst();

    }

}
