package nativeSyncronized.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by aleh on 01.05.14.
 */
public class Parking {
    private List<Place> freePlaces;
    private List<Place> busyPlaces;
    private int countPlaces;

    public Parking(int countPlaces) {
        this.countPlaces = countPlaces;
        freePlaces = new LinkedList<Place>();
        busyPlaces = new LinkedList<Place>();

        for (int i = 0; i < countPlaces; i++) {
            freePlaces.add(new Place(i));
        }
    }

    public synchronized Place getPlace(int maxWaitTime) throws InterruptedException {
        Place freePlace = null;


        long start = System.currentTimeMillis();
        while (freePlaces.size() == 0 && ((System.currentTimeMillis() - start) <= maxWaitTime * 1000)) {
            wait();
        }

        if (freePlaces.size() != 0) {
            freePlace = ((LinkedList<Place>)freePlaces).pollFirst();
            ((LinkedList<Place>)busyPlaces).offerLast(freePlace);
        }

        return freePlace;
    }

    public synchronized boolean freePlace(Place place) {
        boolean status = busyPlaces.remove(place);
        if (status) {
            freePlaces.add(place);
        }
        notifyAll();
        return status;
    }
}
