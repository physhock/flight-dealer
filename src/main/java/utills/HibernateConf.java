package utills;

import businesslogic.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import user.Administrator;
import user.Buyer;
import user.Seller;
import user.User;

class HibernateConf {

    static SessionFactory sessionFactory() {
        return configureHibernates().buildSessionFactory();
    }

    private static Configuration configureHibernates() {

        return new Configuration()
                .addAnnotatedClass(AbstractDomain.class)
                .addAnnotatedClass(Ask.class)
                .addAnnotatedClass(Bet.class)
                .addAnnotatedClass(Deal.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Seller.class)
                .addAnnotatedClass(Buyer.class)
                .addAnnotatedClass(Administrator.class)
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/marketplace")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "postgres");
    }
}
