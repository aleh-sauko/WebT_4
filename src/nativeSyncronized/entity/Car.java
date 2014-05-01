package nativeSyncronized.entity;


/**
 * Created by aleh on 01.05.14.
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
            Place place = parking.getPlace(WAIT_TIME_IN_SECOND);
            if (place != null) {
                System.out.println("Place with number " + place.getNumber() + " become busy.");
            } else {
                System.out.println("Period expired!!! No Places!!! Car go to another parking.");
                return;
            }


            /* Car stay on parking. */
            Thread.currentThread().sleep(stayTimeInSecond * 1000);

            /* Car leave parking and free place on it. */
            boolean status = parking.freePlace(place);
            if (status) {
                System.out.println("Place with number " + place.getNumber() + " become free.");
            } else {
                System.out.println("Place with number " + place.getNumber() + " DOESNT EXSIST OR BUSY.");
            }

        } catch (InterruptedException e) {
            System.out.println("Error in Car thread " + Thread.currentThread());
        }
    }
}
