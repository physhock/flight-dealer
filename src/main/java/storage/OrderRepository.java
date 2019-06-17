package storage;

import businesslogic.Order;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

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
        Session session = newSession();
        session.beginTransaction();
        List<Order> orders = session.createQuery("from Order", Order.class).list();
        session.getTransaction().commit();
        OrderRepository.orders.addAll(orders);
        return OrderRepository.orders;
    }

    public static void removeOrder(Order order) {
        Session session = newSession();
        session.beginTransaction();
        session.remove(order);
        session.getTransaction().commit();
        orders.remove(order);
    }

}
