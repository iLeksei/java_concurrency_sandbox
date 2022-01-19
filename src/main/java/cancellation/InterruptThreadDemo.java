package cancellation;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class InterruptThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        PrimeProducer primeProducer = new PrimeProducer(queue);
        executorService.submit(primeProducer);

        try {
            MILLISECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
//            primeProducer.cancel();    //another option
        }
        System.out.println("Printing prime numbers: ...");
        queue.forEach(System.out::println);
//        executorService.shutdown();
    }

    private static class PrimeProducer extends Thread {
        private final BlockingQueue<BigInteger> queue;

        PrimeProducer(BlockingQueue<BigInteger> queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                BigInteger p = BigInteger.ONE;
                while (!Thread.currentThread().isInterrupted())
                    queue.put(p = p.nextProbablePrime());
            } catch (Exception exception) {
                System.out.println("Thread: " + Thread.class.getSimpleName() + " was interrupted!");
                /* Allow thread to exit */
            }

        }
        public void cancel() {
            System.out.println("Call interruption!");
            interrupt();
        }
    }

}
