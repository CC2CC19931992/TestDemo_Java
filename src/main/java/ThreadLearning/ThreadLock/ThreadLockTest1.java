package ThreadLearning.ThreadLock;

/**
 * 同步和非同步方法是否可以同时调用
 * 同一个类中的synchronized method m1和method m2互斥吗？
 * 结论：t1线程执行m1方法时要去读this对象锁，但是t2线程并不需要读锁，两者各管各的，没有交集（不共用一把锁）
 * @author tc
 * @date 2021/3/5
 */
public class ThreadLockTest1 {

    //synchronized 加在非静态方法上，锁对象即为this(synchronized相当于加锁操作)
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" m1 start...");
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end");
    }

    public void m2(){
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2 ");
    }

    public static void main(String[] args){
        ThreadLockTest1 t=new ThreadLockTest1();

        new Thread(()->t.m1(),"t1").start();
        new Thread(()->t.m2(),"t2").start();
    }
}
