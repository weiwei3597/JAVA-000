package work;
/**
 *
 **/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author weiwei
 * @Date 2020-11-09 22:52
 * @description 使用lock的Condition思路,和等待通知思路基本一样
 **/
public class Work8 {

    static volatile Integer result;

    static final Lock lock = new ReentrantLock();
    static final Condition condition = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {


        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t= new Thread( () ->{
                lock.lock();
                result =sum();
                condition.signal();
                lock.unlock();
        });
        t.start();
        lock.lock();
        condition.await();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        lock.unlock();
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
