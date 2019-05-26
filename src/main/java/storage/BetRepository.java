package storage;

import businesslogic.Bet;

import java.util.ArrayList;

public class BetRepository extends Repository {

    private static BetRepository instance;
    private ArrayList<Bet> bets;

    public BetRepository(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    public static BetRepository getInstance() {
        if (instance == null) {
            instance = new BetRepository(new ArrayList<>());
        }
        return instance;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }


    public void placeBet(Bet bet) {

        this.bets.add(bet);
    }

}
