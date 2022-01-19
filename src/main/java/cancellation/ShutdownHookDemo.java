package cancellation;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ShutdownHookDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println("Submit counter service...");
        executorService.submit(() -> {
            Stream.iterate(1, i -> i + 1)
                    .limit(10)
                    .peek(i -> {
                        System.out.println("Counter will start...");
                    })
                    .forEach(i -> {
                        try {
                            MILLISECONDS.sleep(new Random().nextInt(1000));
                            System.out.println("Counter: " + i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
        });
        executorService.shutdown();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            /**
             * just kill the process and you will see a msg
             */
            @Override
            public void run() {
                System.out.println("Runtime hook message: executorService will be interrupt...");
                executorService.shutdownNow();
            }
        });

    }

}
