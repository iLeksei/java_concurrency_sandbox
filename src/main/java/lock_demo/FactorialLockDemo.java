package lock_demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@RestController
@SpringBootApplication
public class FactorialLockDemo {

    HashMap<Long, Long> cache = new HashMap<>();
    ReadWriteLock cacheLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        SpringApplication.run(FactorialLockDemo.class, args);
    }

    @GetMapping("/factorial/{num}")
    public void calcFactorial(@PathVariable long num) {
        try {
            if (cache.containsKey(num)) {
                System.out.println("factorial was found in cache: " + cache.get(num));
            } else {
                cacheLock.writeLock().lock();
                long result = getFactorial(num);
                cache.put(num, result);
                System.out.println("factorial was calculated: " + result);
                cacheLock.writeLock().unlock();
            }

        } catch (Exception exc) {
            System.out.println("something was wrong!!! Check your code dude!");
            throw new RuntimeException(exc);
        }
    }

    public static long getFactorial(long num) {
        return num != 1 ? num * getFactorial(num - 1) : 1;
    }
}
