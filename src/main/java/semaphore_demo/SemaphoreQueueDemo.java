package semaphore_demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class SemaphoreQueueDemo {

    public static void main(String[] args) {
        List<User> users = generateUsersList();
        Semaphore semaphore = new Semaphore(3);
        ConcurrentLinkedQueue<User> queue = new ConcurrentLinkedQueue<>(users);

        ExecutorService userService = Executors.newCachedThreadPool();
        for (int i = 0; i < users.size(); i++) {
            userService.submit(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(500);
                    User user = queue.poll();
                    System.out.println("Thread " + Thread.currentThread().getName() + " got user: " + user);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        userService.shutdown();

    }

    private static List<User> generateUsersList() {
        List<String> names = Arrays.asList("Bob", "Jessica", "Adam", "Anna",
                "Anne", "Alex", "Freddy", "Olivia", "Amanda", "Kelly", "Jo");
        return names.stream().map(User::new).collect(Collectors.toList());
    }

    private static class User {
        private String name;
        User(String name) { this.name = name; }
        public String getName() { return this.name; };

        @Override
        public String toString() {
            return "User{" +"name='" + name + '\'' + '}';
        }
    }

}
