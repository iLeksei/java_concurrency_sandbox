package volatile_demo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileClassDemo {

    private volatile CounterBox counter = new CounterBox();

    public static void main(String[] args) {
        VolatileClassDemo volatileDemo = new VolatileClassDemo();
        volatileDemo.startDemo();
    }

    public void startDemo() {
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            service.submit(new MyCounter());
        }
        service.shutdown();
    }

    private class MyCounter implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = counter.increment();
            System.out.println("Current state of counter is: " + value);
        }
    }

    private static class CounterBox {
        CounterBox(){}
        private int counter = 0;
        public int increment() {
            counter += 1;
            return this.getCounter();
        }
        public int getCounter() {
            return counter;
        }
        public void resetCounter() {
            counter = 0;
        }
    }

}
