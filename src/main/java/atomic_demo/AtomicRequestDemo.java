package atomic_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@SpringBootApplication
public class AtomicRequestDemo {

//    int counter = new AtomicInteger(0);
    long counter = 0;

    public static void main(String[] args) {
        SpringApplication.run(AtomicRequestDemo.class, args);
    }

    @GetMapping("/increment")
    public synchronized void increment() {
        counter += 1; //.incrementAndGet();
        System.out.println(counter);
    }

    @GetMapping("/show-counter")
    public synchronized long showCounter() {
//        System.out.println(counter.get());
//        return counter.get();
        return counter;
    }
}
