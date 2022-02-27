package ThreadLearning.FutureTaskLearning;

import java.util.concurrent.*;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/3/17
 */
public class FutureTaskDemo3 {
    public static void main(String[] args) throws ExecutionException,InterruptedException {

        //单线程的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //提交Callable任务
        Future<String> result = executorService.submit(
                ()->{
                    System.out.println(Thread.currentThread().getName()+"==========>正在执行");
                    Thread.sleep(3*1000L);
                    return "success";
                }
        );

        System.out.println("result="+result.get());
        // 关闭线程池
        executorService.shutdown();
    }
}
