package work;
/**
 *
 **/

/**
 * @Author weiwei
 * @Date 2020-11-09 22:13
 * @description 异步线程将结果传递到变量中,主线程等待异步线程完成
 **/
public class Work2 {

    static volatile Integer result;

    public static void main(String[] args) throws InterruptedException{
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t= new Thread(() -> result = sum());
        t.start();
        t.join();

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
