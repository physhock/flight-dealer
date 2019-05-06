package storage;

import businesslogic.Ask;
import businesslogic.Bet;
import businesslogic.Deal;
import user.Administrator;

import java.util.ArrayList;
import java.util.Optional;

public class BetRepository {

    private ArrayList<Bet> bets;
    private static BetRepository instance;

    public static BetRepository getInstance() {
        if (instance == null) {
            instance = new BetRepository(new ArrayList<>());
        }
        return instance;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public BetRepository(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    public Optional<Deal> placeBet(Bet bet){

        Optional<Ask> ask = checkForAsk(bet);

        if (ask.isPresent())
            return makeDeal(ask.get(), bet);

        this.bets.add(bet);
        return Optional.empty();

    }

    private Optional<Ask> checkForAsk (Bet bet) {

       return AskRepository.getInstance().getAsks()
                .stream().filter(x -> x.item.equals(bet.item) && x.ask == bet.bet)
                .findFirst();

    }

    private Optional<Deal> makeDeal (Ask ask, Bet bet){
        //Looks for the first free admin in userRepo
        return new Optional<>(new Deal(ask, bet, new Administrator("admin", "123"))) ;
    }
}
