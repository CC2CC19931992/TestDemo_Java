package ThreadLearning.ThreadLock;

/**
 * 静态同步方法和非静态同步方法互斥吗？
 * 答案：不互斥
 * 各玩各的，不是同一把锁，谈不上互斥。
 * 静态同步方法m1锁的对象是TestSync.class对象
 * 非静态同步方法m2锁的对象是this，也就是testSync
 *
 * @author tc
 * @date 2021/3/5
 */
public class ThreadLockTest5 {
    public static void main(String[]args){
        TestSync testSync= new TestSync();
        new Thread(()->testSync.m2(),"t1");
        new Thread(()->TestSync.m1(),"t2");
    }
}

class TestSync{
    public static synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" m1 start...");
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end");
    }

    synchronized void m2(){
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2 ");
    }
}
