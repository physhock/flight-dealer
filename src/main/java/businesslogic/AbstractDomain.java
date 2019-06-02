package businesslogic;

import java.util.Random;

public abstract class AbstractDomain {

    private Long id;

    public AbstractDomain() {
        // TODO
        //  get next id from DB
        this.id = new Random().nextLong();
    }

    public Long getId() {
        return id;
    }
}
