package stamped_demo;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    private static String[] UsersNames = { "Adam", "Bob", "Catarina", "David", "Emily" };
    private HashMap<String, Integer> users = new HashMap<String, Integer>() {{
        put(UsersNames[0], 0);
        put(UsersNames[1], 0);
        put(UsersNames[2], 0);
        put(UsersNames[3], 0);
        put(UsersNames[4], 0);
    }};

    public static void main(String[] args) {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();
        stampedLockDemo.demo();
    }
    private void demo() {
//        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        StampedLock stampedLock = new StampedLock();
        for(int i = 0; i < 20; i++) {
            String user = UsersNames[new Random().nextInt(UsersNames.length)];
            executorService.submit(new MyThread(stampedLock, user));
        }
        executorService.shutdown();

    }

    private class MyThread implements Runnable {
        private final StampedLock lock;
        private final String user;
        MyThread(StampedLock lock, String user) {
            this.lock = lock;
            this.user = user;
        }
        @Override
        public void run() {
            long lock1 = lock.writeLock();
            try {
                Thread.sleep(new Random().nextInt(300));
                users.computeIfPresent(user, (k,v) -> v + 1);
                lock1 = lock.tryConvertToOptimisticRead(lock1);
                System.out.println("User: " + user + " has logged in, counter equals: " + users.get(user));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock(lock1);
            }
        }
    }

}
