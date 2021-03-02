package thread_demo;

import java.util.stream.Stream;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println("My name is: " + this.getClass().getSimpleName());
                Stream.iterate(1, i -> i + 1).limit(10).forEach(System.out::println);
                System.out.println("Goodbye");
            }
        }

        Thread thread = new MyThread();
        thread.start();
        thread.join();
    }

}
