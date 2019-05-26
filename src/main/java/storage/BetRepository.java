package storage;

import businesslogic.Bet;

import java.util.ArrayList;

public class BetRepository extends Repository{

    private ArrayList<Bet> bets;
    private static BetRepository instance;

    public static BetRepository getInstance() {
        if (instance == null) {
            instance = new BetRepository(new ArrayList<>());
        }
        return instance;
    }

    public BetRepository(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }


    public void placeBet(Bet bet) {

        this.bets.add(bet);
    }

}
