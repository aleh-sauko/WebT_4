package concurrentSyncronized.entity;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by aleh on 15.04.14.
 */
public class Parking {

    private ArrayBlockingQueue<Place> freePlaces;
    private ArrayBlockingQueue<Place> busyPlaces;

    public Parking(int countPlaces) {
        freePlaces = new ArrayBlockingQueue<Place>(countPlaces);
        busyPlaces = new ArrayBlockingQueue<Place>(countPlaces);

        for (int i = 0; i < countPlaces; i++) {
            freePlaces.offer(new Place(i));
        }
    }

    public ArrayBlockingQueue<Place> getFreePlaces() {
        return freePlaces;
    }

    public ArrayBlockingQueue<Place> getBusyPlaces() {
        return busyPlaces;
    }
}
