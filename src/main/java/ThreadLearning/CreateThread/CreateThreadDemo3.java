package ThreadLearning.CreateThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/3/16
 */
public class CreateThreadDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 方式1：重写Thread#run()
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "========>正在执行");
            }
        };
        thread.start();

        // 方式2：构造方法传入Runnable实例
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "========>正在执行");
        }).start();

        // 方式3：线程池 + Callable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "========>正在执行");
            Thread.sleep(3 * 1000L);
            return "success";
        });
        String result = submit.get();
        System.out.println("result=======>" + result);
        // 关闭线程池
        executorService.shutdown();
    }
}
