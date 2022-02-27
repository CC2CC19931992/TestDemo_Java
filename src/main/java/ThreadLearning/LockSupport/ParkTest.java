package ThreadLearning.LockSupport;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/3/17
 */
public class ParkTest {

    @Test
    public void testPark() throws InterruptedException {
        // 存储线程
        List<Thread> threadList = new ArrayList<>();

        // 创建5个线程
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                System.out.println("我是" + Thread.currentThread().getName() + ", 我开始工作了~");
                //线程等待
                LockSupport.park(this);
                System.out.println("我是" + Thread.currentThread().getName() + ", 我又活过来了~");
            });
            thread.start();
            threadList.add(thread);
        }

        Thread.sleep(3 * 1000L);
        System.out.println("====== 所有线程都阻塞了，3秒后全部恢复了 ======");

        // unPark()所有线程
        for (Thread thread : threadList) {
            LockSupport.unpark(thread);
        }

        // 等所有线程执行完毕
        Thread.sleep(3 * 1000L);
    }
}
