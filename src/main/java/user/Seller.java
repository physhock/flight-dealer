package user;

import businesslogic.Ask;
import businesslogic.Bet;
import businesslogic.Deal;
import businesslogic.Item;
import services.AssignService;
import storage.AskRepository;
import storage.BetRepository;
import storage.DealRepository;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Optional;

@Entity(name = "Seller")
@Table(name = "sellers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Seller extends User {

    public Seller() {
    }

    public Seller(String userName, String password, UserStatus userStatus) {
        super(userName, password, userStatus);
    }

    public Seller(String userName, String password) {
        super(userName, password, UserStatus.OFFLINE);
    }

    public void makeAsk(Item item, int ask) {
        Ask newAsk = new Ask(item, ask, this);

        Optional<Bet> bet = checkForBet(newAsk);

        if (bet.isPresent()) {
            DealRepository.placeDeal(AssignService.assignAdministratorToDeal(new Deal(newAsk, bet.get())));
            BetRepository.removeBet(bet.get());
        } else
            AskRepository.placeAsk(newAsk);

    }

    private Optional<Bet> checkForBet(Ask ask) {

        return BetRepository.getBets()
                .stream().filter(x -> x.getItem().equals(ask.getItem()) && x.getBet() == ask.getAsk())
                .findFirst();

    }

}
