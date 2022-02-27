package ThreadLearning.ThreadContainer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量的同步容器，拥有put和get方法，以及getCount方法，能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * wait/notifyAll实现：
 *
 * @author tc
 * @date 2021/3/5
 */
public class MyContainer1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    //固定容量,假定最多10个元素
    final private int MAX = 10;
    private int count = 0;

    //put方法
    public synchronized void put(T t) {
        // 想想为什么用while而不是用if？
        // 原因：
        // 如果此处用if的话，满足条件后意味着if继续往下走，那么会跳出if语句块，起不到阻塞的作用
        // 在这个线程休眠期间，极有可能条件还是成立的，还应继续阻塞，这时不应该继续向下执行。
        // 此如果重新读取判断条件，即可避免重复执行，所以用while才是合适的。
        // while的话，线程被唤醒后得到执行权会再次进行判断，这在高并发情景下是必须的。
        while (lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        ++count;
        //通知消费者线程进行消费
        this.notifyAll();
    }

    //get方法
    public synchronized T get() {
        T t = null;
        while(lists.size() == 0) {
            try {
                System.out.println(Thread.currentThread().getName()+ "线程消费等待");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count --;
        //通知生产者进行生产
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        //启动消费者线程
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++){
                    System.out.println("消费线程 "+ Thread.currentThread().getName() +"，消费内容"+c.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++){
                    System.out.println("生产线程 "+ Thread.currentThread().getName() +"，生产内容:"+Thread.currentThread().getName() + " " + j);
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }
}
