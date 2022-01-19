package executor_demo;


import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class CompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Integer[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletionService<Integer> service = new ExecutorCompletionService<>(executorService);
        Stream.of(arr)
            .parallel()
            .forEach((i) -> {
                try {
                    MILLISECONDS.sleep(new Random().nextInt(5200));
                    System.out.println("Submit i: " + i);
                    service.submit(() -> { return i; });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        for (int i = 0; i < arr.length; i++) {
//            Future<Integer> num = service.take();
            Future<Integer> num = service.poll(1, NANOSECONDS);
            System.out.println("Got num: " + num.get());
        }

        executorService.shutdown();
    }

}
