package ThreadLearning.CreateThread;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/3/4
 */
public class ThreadLearningTest {

    public static void main(String[] args){
        //这里首先显式线程创建 传入的是实现了Runnable接口的匿名类
        //而这里显式创建线程后面接了个重写Thread里的run方法。那么 这就相当于用了继承了Thread的匿名类。
        //重写run后，就没有了Thread的run方法里的target.run()，而是跑的重写的run方法里的逻辑。
        //所以即使传入Runnable对象，也不会调用它里面的run()。
        //当然如果再重写的run方法里加上super.run();那么也会打印"Runnable's run method is running"
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Runnable's run method is running");
                    }
                })
        {
            @Override
            public void run() {
                //super.run();
                System.out.println("Thread's run method is running");
            }
        }.start();
    }

}

