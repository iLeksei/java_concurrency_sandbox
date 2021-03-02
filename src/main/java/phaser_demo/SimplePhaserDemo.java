package phaser_demo;

import java.util.concurrent.Phaser;

public class SimplePhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        phaser.register();
    }
}
