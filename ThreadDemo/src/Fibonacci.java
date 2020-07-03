import java.util.concurrent.RecursiveTask;

/**
 * @program:untitled
 * @description:
 * @author:Juwenchao
 * @date:2020-07-03 16:33:40
 */
public class Fibonacci extends RecursiveTask<Integer> {
    int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        if (n<=1){
            return n;
        }
        Fibonacci f1 = new Fibonacci(n-1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n-2);
        f2.fork();
        return f1.join()+f2.join();

    }
}
