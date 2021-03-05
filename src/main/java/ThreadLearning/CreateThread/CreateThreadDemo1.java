package ThreadLearning.CreateThread;

/**
 * 创建线程的方式1：继承Thread类，重写run()方法
 *
 * @author tc
 * @date 2021/3/4
 */
public class CreateThreadDemo1 extends Thread{
    public static void main(String[] args) {
        // ThreadDemo1继承了Thread类，并重写run()
        CreateThreadDemo1 t = new CreateThreadDemo1();
        // 开启线程：t线程得到CPU执行权后会执行run()中的代码
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}
