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
        //想想为什么用while而不是用if？
        while(lists.size() == MAX) {
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
                System.out.println("================");
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
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }
}
