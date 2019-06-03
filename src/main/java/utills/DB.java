package utills;

import dao.ItemMapper;
import org.hibernate.Session;
import storage.AskRepository;
import storage.BetRepository;
import storage.DealRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DB {

    private ItemMapper itemMapper;
    private AskRepository askRepository;
    private BetRepository betRepository;
    private DealRepository dealRepository;

    public ItemMapper getItemMapper() {
        return itemMapper;
    }

    public void setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public AskRepository getAskRepository() {
        return askRepository;
    }

    public void setAskRepository(AskRepository askRepository) {
        this.askRepository = askRepository;
    }

    public BetRepository getBetRepository() {
        return betRepository;
    }

    public void setBetRepository(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public DealRepository getDealRepository() {
        return dealRepository;
    }

    public void setDealRepository(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }


    public void startDB() {

        String url = "jdbc:postgresql://localhost:5432/marketplace";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("ssl", "false");
        try {
            Connection connection = DriverManager.getConnection(url, props);
            sessionFactoryHibernate();
            System.out.println("DB connected");
            itemMapper = new ItemMapper(connection);

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }


    private void sessionFactoryHibernate() {

        Session session = HibernateConf.sessionFactory().openSession();
        System.out.println("Hibernate connected " + session.isConnected());
        askRepository = new AskRepository(session, new ArrayList<>());
        betRepository = new BetRepository(session, new ArrayList<>());
        dealRepository = new DealRepository(session, new ArrayList<>());


    }

}
