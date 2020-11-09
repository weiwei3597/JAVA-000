package work;
/**
 *
 **/

/**
 * @Author weiwei
 * @Date 2020-11-09 22:07
 * @description 异步线程将结果传递到变量中,主线程一直循环获取改变量,变量没有值就堵塞
 **/
public class Work1 {

    static volatile Integer result;

    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t= new Thread( () -> result = sum());
        t.start();
        while (result==null);

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
