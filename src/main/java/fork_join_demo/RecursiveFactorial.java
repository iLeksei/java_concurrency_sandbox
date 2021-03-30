package fork_join_demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class RecursiveFactorial extends RecursiveTask<Long> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RecursiveFactorial rf = new RecursiveFactorial(33);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = forkJoinPool.submit(rf);
        System.out.println(task.get().toString());
    }


    private Long n;

    RecursiveFactorial(long n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 1) {
            return n;
        }
        RecursiveFactorial recursiveFactorial = new RecursiveFactorial(n - 1);
        recursiveFactorial.fork();
        return n * n + recursiveFactorial.compute();
    }
}
