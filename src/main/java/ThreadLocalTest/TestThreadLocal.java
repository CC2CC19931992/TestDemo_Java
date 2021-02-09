package ThreadLocalTest;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/30
 */
public class TestThreadLocal {
    //创建两个ThreadLocal实例并指定泛型，分别存储Long/String类型数据
    private static ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    private static ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    //set方法，因为只是内部调用，用了private
    private void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    //get方法
    private long getLong() {
        return longLocal.get();
    }

    //get方法
    private String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        //------main线程执行开始--------
        final TestThreadLocal test = new TestThreadLocal();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread thread = new Thread() {
            @Override
            public void run() {
                //-------Thread-0线程执行开始--------
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
                //-------Thread-0线程执行结束--------
            }
        };
        thread.start();
        //thread.join():用来指定当前主线程等待其他线程执行完毕后,再来继续执行Thread.join()后面的代码
        thread.join();
        System.out.println(test.getLong());
        System.out.println(test.getString());
        //------main线程执行结束--------
    }
}
