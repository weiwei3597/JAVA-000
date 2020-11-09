package work;
/**
 *
 **/

/**
 * @Author weiwei
 * @Date 2020-11-09 22:45
 * @description 使用锁思路,让异步的线程拿到锁,主线程没有锁就会堵塞到输出结果
 **/
public class Work7 {

    static final String  lock = "1";

    static volatile  Integer result;



    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t= new Thread( () ->{
            synchronized (lock){
                result =sum();
            }
        });
        t.start();
        Thread.sleep(1000);
        synchronized (lock){
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为："+result);

            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        }

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
