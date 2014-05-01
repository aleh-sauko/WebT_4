package nativeSyncronized;

import nativeSyncronized.entity.Car;
import nativeSyncronized.entity.Parking;

/**
 * Created by aleh on 01.05.14.
 */
public class NativeLauncher {

    public static void main(String[] args) {

        Parking parking = new Parking(100);
        try {
            for (int i = 0; i < 1000; i++) {
                Thread.currentThread().sleep(1);
                new Car(parking, 1).start();
            }
        } catch (InterruptedException ex) {
            System.out.println("Error in NativeLauncher thread " + Thread.currentThread());
        }
    }
}
