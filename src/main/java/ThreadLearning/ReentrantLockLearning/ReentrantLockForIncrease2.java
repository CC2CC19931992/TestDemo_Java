package ThreadLearning.ReentrantLockLearning;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock的多线程累加操作
 *
 * @author tc
 * @date 2021/3/8
 */
public class ReentrantLockForIncrease2 {
    //初始化ReentrantLock
    public static ReentrantLock reentrantLock = new ReentrantLock();
    static int cnt = 0;
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //加锁
                reentrantLock.lock();
                try {
                    int n = 10000;
                    while(n>0){
                        cnt++;
                        n--;
                    }
                }catch (Exception ex){

                }finally {
                    //执行完毕后释放锁
                    reentrantLock.unlock();
                }
            }
        };
        Thread t1  = new Thread(r);
        Thread t2  = new Thread(r);
        Thread t3  = new Thread(r);
        Thread t4  = new Thread(r);
        Thread t5  = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            //等待足够长的时间 确保上述线程均执行完毕
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cnt);
    }

}
