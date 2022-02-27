package ThreadLearning.ReentrantLockLearning;

/**
 * 未使用ReentrantLock进行多线程累加操作
 *
 * @author tc
 * @date 2021/3/8
 */
public class ReentrantLockForIncrease1 {
    static int cnt = 0;
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int n = 10000;
                while(n>0){
                    cnt++;
                    n--;
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
