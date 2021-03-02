package consumer_producer_demo;

import java.util.Random;
import java.util.concurrent.*;

public class RandomProducerDemo extends Random {
    private final String[] tasks = {"homework", "dinner", "shopping", "movie theater", "sport activities"};
    private final CopyOnWriteArrayList<String> processed = new CopyOnWriteArrayList<>();
    private BlockingQueue<String> queue;

    RandomProducerDemo(BlockingQueue<String> queue) {
        this.queue = queue;
    }


    public void produce() throws InterruptedException {
        Thread.sleep(2000);
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < tasks.length; i++) {
            service.submit(() -> {
                String task = getRandomTask();
                try {
                    Thread.sleep(nextInt(3000));
                    System.out.println("produce task: " + task);
                    queue.put(task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }

    private String getRandomTask() {
        String element = tasks[nextInt(tasks.length)];
        while (processed.contains(element)) {
            element = tasks[nextInt(tasks.length)];
        }
        processed.add(element);
        return element;
    }

}
