package ThreadLearning.ThreadLock;

import java.util.concurrent.TimeUnit;

/**
 * 同一个类中synchronized method m1中可以调用synchronized method m2吗？
 * 一个同步方法(带有synchronized关键字的方法)可以调用另外一个同步方法，
 * 一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * synchronized是可重入锁，可以粗浅地理解为同一个线程在已经持有该锁的情况下，
 * 可以再次获取锁，并且会在某个状态量上做+1操作
 *
 * @author tc
 * @date 2021/3/5
 */
public class ThreadLockTest2 {

    public synchronized void m1(){
        System.out.println(" m1 start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        m2();
        System.out.println(" m1 end...");
    }

    synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(" m2 ");
    }

    public static void main(String[] args){
        ThreadLockTest2 t=new ThreadLockTest2();
        new Thread(()->t.m1(),"t1").start();
    }
}
