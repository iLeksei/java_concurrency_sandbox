package executor_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledExecutor {

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(1);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " works by schedule..." + counter);
            counter.incrementAndGet();
        }, 1, 1000, TimeUnit.MILLISECONDS);
        while (true) {
            if (counter.get() == 10) {
                executorService.shutdown();
            }
        }
    }

}
