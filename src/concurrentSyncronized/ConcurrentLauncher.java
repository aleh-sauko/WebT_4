package concurrentSyncronized;

import concurrentSyncronized.entity.Car;
import concurrentSyncronized.entity.Parking;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aleh on 16.04.14.
 */
public class ConcurrentLauncher {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Parking parking = new Parking(100);
        try {

            for (int i = 0; i < 1000; i++) {
                Thread.currentThread().sleep(1);
                executor.execute(new Car(parking, 1));
            }
        } catch (InterruptedException ex) {
            System.out.println("Error in ConcurrentLauncher thread " + Thread.currentThread());
        } finally {
            executor.shutdown();
        }
    }
}
