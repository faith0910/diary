
import java.util.concurrent.*;

public class Test{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            final ForkJoinTask<Integer> task = forkJoinPool.submit(new Fibonacci(i));
            System.out.println(task.get());
            ThreadPoolExecutor pool = new ThreadPoolExecutor(5,10,6, TimeUnit.SECONDS,new LinkedBlockingQueue<>());


        }
    }
}
