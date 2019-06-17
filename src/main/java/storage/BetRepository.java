package storage;

import businesslogic.Bet;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BetRepository extends Repository {

    private static ArrayList<Bet> bets;

    public BetRepository() {
       bets = new ArrayList<>();
    }

    public static ArrayList<Bet> getBets() {
        Session session = newSession();
        session.beginTransaction();
        List<Bet> bets_raw = session.createQuery("from Bet", Bet.class).list();
        session.getTransaction().commit();
        session.close();
        bets.addAll(bets_raw);
        return bets;
    }

    public static void removeBet(Bet bet) {
        Session session = newSession();
        session.beginTransaction();
        session.remove(bet);
        session.getTransaction().commit();
        bets.remove(bet);
    }

    public static void placeBet(Bet bet) {
        Session session = newSession();
        session.beginTransaction();
        session.save(bet);
        session.getTransaction().commit();

        bets.add(bet);
    }

}
