package storage;

import businesslogic.Bet;
import org.hibernate.Session;

import java.util.ArrayList;

public class BetRepository extends Repository {

    private ArrayList<Bet> bets;

    public BetRepository(Session session, ArrayList<Bet> bets) {
        super(session);
        this.bets = bets;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void removeBet(Bet bet) {
        bets.remove(bet);
    }

    public void placeBet(Bet bet) {

        this.bets.add(bet);
    }

}
