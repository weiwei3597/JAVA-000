package work;
/**
 *
 **/

import java.util.concurrent.Semaphore;

/**
 * @Author weiwei
 * @Date 2020-11-09 22:35
 * @description 使用Semaphore,只让一个线程执行,并且sleep主线程,保证异步线程先执行semaphore.acquire()
 **/
public class Work6 {

    private static final Semaphore semaphore = new Semaphore(1);

    static volatile Integer result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t= new Thread( () ->{
            try {
                semaphore.acquire();
                result =sum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        });
        t.start();
        Thread.sleep(1000);
        semaphore.acquire();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        semaphore.release();
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
