package barrier_demo;

import java.util.concurrent.*;

public class SimpleBarrierDemo {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, () -> {
        try {
            Thread.sleep(500);
            System.out.println("Паром переправил автомобили!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 9; i++) {
            service.submit(new Thread(new Car(i)));
            Thread.sleep(400);
        }
        service.shutdown();
    }

    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Car №%d came to barrier.\n", carNumber);
                BARRIER.await();
                System.out.printf("Car №%d continued.\n", carNumber);
            } catch (Exception e) {
            }
        }
    }
}
