package work;
/**
 *
 **/

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author weiwei
 * @Date 2020-11-09 23:10
 * @description CyclicBarrier思路 让主线程成为最后一个完成的线程
 **/
public class work10 {

    static volatile Integer result;

    static CyclicBarrier barrier  = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t= new Thread( () -> {
            try {
                result = sum();
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t.start();
        Thread.sleep(1000);
        barrier.await();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

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
