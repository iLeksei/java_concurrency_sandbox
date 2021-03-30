package future_demo;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ArrayFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<Future<String>> list = new ArrayList<>();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            Future<String> future = service.submit(new MyCallable());
            list.add(future);
        }

        for (Future<String> f : list) {
            System.out.println(LocalDateTime.now() + " : " +f.get());
        }
        service.shutdown();
    }

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(1500);
            return Thread.currentThread().getName();
        }
    }
}
