package sync_demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class SyncPutIfAbsentDemo<E> {
    private List<E> list = Collections.synchronizedList(new ArrayList<E>());

    /**
     *  don't use synchronized key-word in method,
     *  because there will be another monitor(lock)
     */
    public boolean putIfAbsent(E v) {
        synchronized (list) {
            boolean absent = !list.contains(v);
            if (absent) {
                System.out.println("value will be added: " + v);
                list.add(v);
            } else {
                System.out.println("value will be not added: " + v);
            }
            return absent;
        }
    }

    public static void main(String[] args) {
        String[] users = {"Abe", "Bob", "Cody", "Daniel"};
        SyncPutIfAbsentDemo<String> arr = new SyncPutIfAbsentDemo<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Stream.iterate(1, i -> i + 1)
                .limit(10)
                .forEach((i) -> {
                    String currentUser = users[new Random().nextInt(users.length)];
                    executorService.submit(() -> {
                        arr.putIfAbsent(currentUser);
                    });
                });
        executorService.shutdown();
    }


}
