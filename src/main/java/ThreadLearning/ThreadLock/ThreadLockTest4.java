package ThreadLearning.ThreadLock;

import java.util.concurrent.TimeUnit;

/**
 * 子类同步方法synchronized method m可以调用父类的synchronized method m吗（super.m()）？
 * 子类对象初始化前，会调用父类构造方法，在结构上相当于包裹了一个父类对象，用的都是this锁对象
 * 所以是同一个锁，而synchronized又是可重入锁，所以答案是可以的
 *
 * @author tc
 * @date 2021/3/5
 */
public class ThreadLockTest4 {

    synchronized void m(){
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args){
        new TT().m();
    }
}

class TT extends ThreadLockTest4{

    @Override
    synchronized void m(){
        System.out.println("child start");
        super.m();
        System.out.println("child end");
    }
}
