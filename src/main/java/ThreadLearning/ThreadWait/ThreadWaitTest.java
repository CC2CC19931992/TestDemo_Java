package ThreadLearning.ThreadWait;

import java.util.LinkedList;

/**
 * 测试同一个线程 第一个循环wait了 后面的还会不会执行
 *
 * @author tc
 * @date 2021/3/5
 */
public class ThreadWaitTest {
    public static void main(String []args){
        TestWaitClass ts= new TestWaitClass();
//        for(int i=0; i<10; i++){
//            new Thread(()->{
//                ts.set("aaa");
//            }).start();
//        }

//        ts.set("aaa");
//        System.out.println("会不会来我这里");
//        ts.set("aaa");

        for(int i=0; i<10; i++){
            System.out.println("会不会来我这里"+i);
            ts.set("aaa");
        }
    }
}

class TestWaitClass{
    public synchronized String set(String str) {
        if(str=="aaa"){
            try {
                System.out.println("================");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String t = str;
        return t;
    }
}