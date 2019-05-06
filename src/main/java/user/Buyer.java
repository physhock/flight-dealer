package user;

import businesslogic.Bet;
import storage.BetRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Buyer extends User {

    private ArrayList<Bet> bets;

    public Buyer(String userName, String password) {
        super(userName, password);
        searchBets();
    }

    private void searchBets(){
        bets = BetRepository.getInstance().getBets().stream()
                .filter(bet -> bet.getBuyer().getUserName().equals(this.getUserName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }



}
