package ThreadLearning.ThreadLock;

import java.util.concurrent.TimeUnit;

/**
 * 一个类里的get和set方法加上了synchronized，那么在不同的线程下，get和set方法会互斥么?
 * 答案：会互斥，因为synchronized 修饰方法的时候，锁的对象为this.也就是在下面的示例中，锁的对象是user
 * 如代码所示，t1和t2两个线程分别调用set和get方法。
 * 当t1线程进行set操作时，这段代码其实是synchronized(user) 然后再是this.name=name的赋值操作
 * t2线程进入到get方法时，也是synchronized(user)，但是因为t1的set方法还没执行完，那么这段代码则先暂定，等待t1执行完毕
 *
 * @author tc
 * @date 2021/3/5
 */
public class ThreadLockTest3 {
    public static void main(String[] args){
        User user = new User();
        new Thread(()->user.setName("1111"),"t1").start();
        new Thread(()->System.out.println(user.getName()),"t2").start();

    }
}

class User{
    private String name;

    public synchronized String getName() {
//        try {
//            Thread.sleep(3000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        return name;
    }

    public synchronized void setName(String name) {
        try {
            Thread.sleep(100000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.name = name;
    }
}
