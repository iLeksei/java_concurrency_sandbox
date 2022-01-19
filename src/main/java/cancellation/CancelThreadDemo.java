package cancellation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CancelThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        List<BigInteger> nums = new CancelThreadDemo().aSecondOfPrimes();
        nums.stream().forEach(System.out::println);
    }

    List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }

    private class PrimeGenerator implements Runnable {
        private final List<BigInteger> primes = new ArrayList<>();
        private volatile boolean cancelled = false;

        @Override
        public void run() {
            BigInteger p = BigInteger.ONE;
            while (!cancelled ) {
                p = p.nextProbablePrime();
                synchronized (this) {
                    primes.add(p);
                }
            }
        }
        public void cancel() {
            cancelled = true;
            System.out.println("Cancellation...");
        }
        public synchronized List<BigInteger> get() {
            return new ArrayList(primes);
        }
    }
}
