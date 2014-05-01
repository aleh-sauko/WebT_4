package concurrentSyncronized.entity;

import java.util.concurrent.TimeUnit;

/**
 * Created by aleh on 17.04.14.
 */
public class Car extends Thread {

    private static final int WAIT_TIME_IN_SECOND = 5;

    private Parking parking;
    private int stayTimeInSecond;

    public Car(Parking parking, int stayTimeInSecond) {
        this.parking = parking;
        this.stayTimeInSecond = stayTimeInSecond;
    }

    @Override
    public void run() {
        try {

            /* Car arrive on parking to find place. */
            Place freePlace = parking.getFreePlaces().poll(WAIT_TIME_IN_SECOND, TimeUnit.SECONDS);
            if (freePlace != null) {
                boolean status = parking.getBusyPlaces().offer(freePlace);
                if (status) {
                    System.out.println("Place with number " + freePlace.getNumber() + " become busy.");
                }
            } else {
                System.out.println("Period expired!!! No Places!!! Car go to another parking.");
            }


            /* Car stay on parking. */
            Thread.currentThread().sleep(stayTimeInSecond * 1000);

            /* Car leave parking and free place on it. */
            Place busyPlace = parking.getBusyPlaces().poll(WAIT_TIME_IN_SECOND, TimeUnit.SECONDS);
            if (busyPlace != null) {
                boolean status = parking.getFreePlaces().offer(busyPlace, WAIT_TIME_IN_SECOND, TimeUnit.SECONDS);

                if (status) {
                    System.out.println("Place with number " + busyPlace.getNumber() + " become free.");
                }

            }

        } catch (InterruptedException e) {
            System.out.println("Error in Car thread " + Thread.currentThread());
        }
    }

}
