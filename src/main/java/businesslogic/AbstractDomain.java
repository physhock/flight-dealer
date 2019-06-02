package businesslogic;

public abstract class AbstractDomain {

    private Long id;

    public AbstractDomain() {
        // TODO
        //  get next id from DB
        this.id = Long.valueOf(-1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
