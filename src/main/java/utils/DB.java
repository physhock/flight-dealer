package utils;

import dao.ItemMapper;
import storage.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private ItemMapper itemMapper;
    private ItemRepository itemRepository;
    private AskRepository askRepository;
    private BetRepository betRepository;
    private DealRepository dealRepository;
    private UserRepository userRepository;

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void startDB() {

        String url = "jdbc:postgresql://localhost:5432/marketplace";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("ssl", "false");
        try {
            Connection connection = DriverManager.getConnection(url, props);
            System.out.println("DB connected");
            itemMapper = new ItemMapper(connection);
            initRepositories();

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    private void initRepositories() {
        askRepository = new AskRepository();
        betRepository = new BetRepository();
        dealRepository = new DealRepository();
        userRepository = new UserRepository();
        itemRepository = new ItemRepository(itemMapper);

    }
}
