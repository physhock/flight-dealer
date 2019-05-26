package storage;

import businesslogic.Deal;

import java.util.ArrayList;

public class DealRepository extends Repository {
    private static DealRepository instance;
    private ArrayList<Deal> deals;

    public DealRepository(ArrayList<Deal> objects) {
        this.deals = objects;
    }

    public static DealRepository getInstance() {
        if (instance == null) {
            instance = new DealRepository(new ArrayList<>());
        }
        return instance;
    }

    public ArrayList<Deal> getDeals() {
        return deals;
    }


}
