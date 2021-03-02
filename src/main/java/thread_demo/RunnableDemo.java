package thread_demo;

import java.util.stream.Stream;

public class RunnableDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main has started");
        Thread thread = new Thread(()-> {
            System.out.println("Start");
            Stream.iterate(1, i -> i + 1).limit(10).forEach(System.out::println);
            System.out.println("Finish!");
        });
        thread.start();
        thread.join();
        System.out.println("main has finished");
    }

}
