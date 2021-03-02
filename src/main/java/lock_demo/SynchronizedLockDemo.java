package lock_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;

@RestController
@SpringBootApplication
public class SynchronizedLockDemo {

    int counter= 0;

    public static void main(String[] args) {
        SpringApplication.run(SynchronizedLockDemo.class, args);
    }

    @GetMapping("/increment")
    public void increment() {
        synchronized (this) {
            counter+=1;
            System.out.println(counter);
        }
    }

}
