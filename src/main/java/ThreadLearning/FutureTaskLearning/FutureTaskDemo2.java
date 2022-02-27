package ThreadLearning.FutureTaskLearning;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * FutureTask
 *
 * @author tc
 * @date 2021/3/16
 */
public class FutureTaskDemo2 {
    public static void main(String[] args) {
        //FutureTask包装Runnable(通过构造器)
        FutureTask<String> runnable = new FutureTask<>(new Runnable(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName()+"======>正在执行");
            }
        },"success");

        //FutureTask包装Callable(通过构造器)
        FutureTask<String> callable= new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(3*1000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return "success";
            }
        });


    }
}
