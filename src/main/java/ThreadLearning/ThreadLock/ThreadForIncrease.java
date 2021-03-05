package ThreadLearning.ThreadLock;

/**
 * 最终输出的结果肯定小于50000 因为cnt++不是原子性操作
 *
 * @author tc
 * @date 2021/3/5
 */
public class ThreadForIncrease {
    //共享数据cnt
    static int cnt = 0;
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //有多条语句操作共享数据
                int n = 10000;
                while(n>0){
                    cnt++;
                    n--;
                }
            }
        };
        //多线程环境
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
