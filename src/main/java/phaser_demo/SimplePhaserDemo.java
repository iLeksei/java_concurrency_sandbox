package phaser_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.stream.Stream;

public class SimplePhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Stream.of(new int[]{1, 2, 3})
                .forEach(i -> {
                    executorService.submit(() -> {

                    });
                });

        phaser.register();
    }
}
