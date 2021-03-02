package concurrent_collections_demo;

import java.util.Random;
import java.util.concurrent.*;

/**
 * it's a not a good demo, because sometimes we can get race-condition!
 * Check it at System.out
 */
public class ConcHashMapDemo {

    private static String[] UsersNames = { "Adam", "Bob", "Catarina", "David", "Emily" };
    private ConcurrentHashMap<String, Integer> users = new ConcurrentHashMap<String, Integer>() {{
        put(UsersNames[0], 0);
        put(UsersNames[1], 0);
        put(UsersNames[2], 0);
        put(UsersNames[3], 0);
        put(UsersNames[4], 0);
    }};

    public static void main(String[] args) {
        ConcHashMapDemo mapDemo = new ConcHashMapDemo();
        mapDemo.demo();
    }

    private void demo() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 20; i++) {
            String user = UsersNames[new Random().nextInt(5)];
            executorService.submit(new UserLoginCounter(user));
        }
        executorService.shutdown();
    }

    private class UserLoginCounter implements Runnable {
        private String user = "";
        UserLoginCounter(String user) { this.user = user; };
        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(800));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            users.computeIfPresent(user, (k,v) -> v + 1);
            System.out.println("User: " + user + " has logged in, counter equals: " + users.get(user));
        }
    }
}
