package future_demo;

import java.util.concurrent.*;

public class SimpleFutureDemo {

    public static void main(String[] args) throws Exception {
        new SimpleFutureDemo().showDemo();
    }

    public void showDemo() throws Exception {
        FutureTask<Integer> future = new FutureTask<>(() -> {
            System.out.println("will sleep 2.5 sec");
           Thread.sleep(2500);
//           throw new RuntimeException("Synthetic exception");
           return 123;
       });
        future.run();
// Or it's possible to run by Thread
//        Thread th = new Thread(future);
//        th.start();
        Integer integer = future.get();
        System.out.println("result: " + integer);
//        th.join();

    }



}
