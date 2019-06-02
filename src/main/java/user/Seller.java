package user;

import businesslogic.Ask;
import businesslogic.Bet;
import businesslogic.Deal;
import businesslogic.Item;
import services.AssignService;
import storage.AskRepository;
import storage.BetRepository;
import storage.DealRepository;

import java.util.Optional;

public class Seller extends User {

    public Seller(String userName, String password) {
        super(userName, password);
    }

    public void makeAsk(Item item, int ask) {
        Ask newAsk = new Ask(this, item, ask);

        Optional<Bet> bet = checkForBet(newAsk);

        if (bet.isPresent()) {
            DealRepository.getInstance().placeDeal(AssignService.assignAdministratorToDeal(new Deal(newAsk, bet.get())));
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
