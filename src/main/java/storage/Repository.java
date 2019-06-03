package storage;

import org.hibernate.Session;

public abstract class Repository {

    private Session session;

    public Repository(Session session) {
        this.session = session;
    }

}
