package LambdaTest;

import java.util.concurrent.TimeUnit;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/21
 */
public class LamdaTest4 {
    public static void main(String[] args) throws InterruptedException {
        // 在匿名内部类的外面定义一个String变量
        String str = "hello";
        // 构造一个匿名内部类对象
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(str);
                System.out.println("this==>"+this);
            }
        };

        new Thread(r).start();

        Test test = new Test();
        test.testClosure();

        TimeUnit.SECONDS.sleep(1);
    }
}

class Test{
    public void testClosure() throws InterruptedException {
        // 在匿名内部类的外面定义一个String变量
        final String str = "hello";

        Runnable r = ()->{
            System.out.println(str);
            System.out.println("this==>"+this);
        };

        new Thread(r).start();

        TimeUnit.SECONDS.sleep(1);
    }
}
