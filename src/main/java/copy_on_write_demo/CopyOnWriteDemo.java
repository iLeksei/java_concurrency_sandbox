package copy_on_write_demo;


import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class CopyOnWriteDemo {
    private final CopyOnWriteArraySet<String> users_online = new CopyOnWriteArraySet<>();
    private final String[] all_users = { "Adam", "Bob", "Catarina", "David", "Emily", "Franklyn", "Ginger" };


    public static void main(String[] args) {
        CopyOnWriteDemo cowd = new CopyOnWriteDemo();
        cowd.demo();
    }

    public void demo() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Stream.iterate(1, i -> i + 1)
            .limit(20)
            .forEach((i) -> {
                executorService.submit(() -> {
                    String currentUser = all_users[new Random().nextInt(all_users.length)];
                    if (users_online.contains(currentUser)) {
                        System.out.println("User: " + currentUser + " has disconnected");
                        users_online.remove(currentUser);
                    } else {
                        System.out.println("User: " + currentUser + " has connected");
                        users_online.add(currentUser);
                    }
                    System.out.println("Users online: " + users_online.toString());
                });
            });
        executorService.shutdown();
    }

}
