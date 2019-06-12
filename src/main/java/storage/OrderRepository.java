package storage;

import businesslogic.Order;
import org.hibernate.Session;
import user.Buyer;

import java.util.ArrayList;

public class OrderRepository extends Repository {

    private static ArrayList<Order> orders = new ArrayList<>();

    public static void placeOrder(Order order) {
        Session session = newSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        order.getBuyer().addOrder(order);
        orders.add(order);
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static void removeOrder(Order order) {
        Session session = newSession();
        session.beginTransaction();
        session.remove(order);
        session.getTransaction().commit();
        orders.remove(order);
    }

}
