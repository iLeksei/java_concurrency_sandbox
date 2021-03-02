package sync_demo;

import java.util.HashSet;
import java.util.Random;

/**
 * Example of using non-thread-safe Object in thread-safe object
 */
public class SyncDemo extends Thread {

    private volatile HashSet<String> users = new HashSet<>();
    public String[] names = {"Abe", "Bob", "Carl", "David", "Emily", "Franklyn"};

    public static void main(String[] args) throws InterruptedException {
        SyncDemo sd = new SyncDemo();
        for (int i = 0; i < 10; i++) {
            sd.addUser(sd.names[new Random().nextInt(sd.names.length)]);
        }

    }

    private synchronized void addUser(String name) throws InterruptedException {
        Thread.sleep(new Random().nextInt(1000));
        System.out.println("User: " + name + " will be add");
        users.add(name);
        System.out.println(String.join(",", users));
    }
    private synchronized void removeUser(String name) throws InterruptedException {
        Thread.sleep(new Random().nextInt(1000));
        System.out.println("User: " + name + " will be removed");
        users.remove(name);
        System.out.println(String.join(",", users));
    }
}
