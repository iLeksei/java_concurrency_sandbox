package consumer_producer_demo;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerDemo {
    private BlockingQueue<String> queue;

    ConsumerDemo(BlockingQueue<String> queue) {
     this.queue = queue;
    }

    public void consume() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
//        service.awaitTermination(10, TimeUnit.SECONDS);
        while (true) {
            service.submit(() -> {
                try {
                    Thread.sleep(new Random().nextInt(5000));
                    String task = queue.take();
                    System.out.println("current task: " + task);
                } catch (InterruptedException e) {
                    System.out.println("timeout!");
                    e.printStackTrace();
                }
            });
        }
    }

}
