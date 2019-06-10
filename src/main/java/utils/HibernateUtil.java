package utils;

import businesslogic.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import user.Administrator;
import user.Buyer;
import user.Seller;
import user.User;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                setUp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static void addClasses(MetadataSources metadataSources) {
        metadataSources
                .addAnnotatedClass(AbstractDomain.class)
                .addAnnotatedClass(Ask.class)
                .addAnnotatedClass(Bet.class)
                .addAnnotatedClass(Deal.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Seller.class)
                .addAnnotatedClass(Buyer.class)
                .addAnnotatedClass(Administrator.class);
    }

    private static void setUp() throws Exception {

        final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .loadProperties("/hibernate.properties")
                .build();

        try {
            MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
            addClasses(metadataSources);
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            throw new Exception(e);
        }
    }

}