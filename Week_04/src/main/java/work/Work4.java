package work;
/**
 *
 **/

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Author weiwei
 * @Date 2020-11-09 22:26
 * @description 使用FutureTask获取结果
 **/
public class Work4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        FutureTask<Integer> futureTask = new FutureTask<Integer>(Work4::sum);
        new Thread(futureTask).start();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+futureTask.get());

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }


    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
