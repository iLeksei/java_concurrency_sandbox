package latch_demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        new CountDownLatchDemo().showDemo(5);
    }

    private void showDemo(int nThreads) throws Exception {
        CountDownLatch startGate = new CountDownLatch(1);
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < nThreads; i++) {
            service.submit(() -> {
                try {
                    startGate.await();
                    Thread.sleep(new Random().nextInt(1500));
                    System.out.println("working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        System.out.println("will sleep 5 seconds and unlock latch");
        Thread.sleep(5000);
        System.out.println("countDown");
        startGate.countDown();
        service.shutdown();
    }
}
