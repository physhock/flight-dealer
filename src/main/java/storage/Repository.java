package storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

abstract class Repository {

    private static SessionFactory sessionFactory;

    Repository() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    static Session newSession(){ return  sessionFactory.openSession(); }

}
