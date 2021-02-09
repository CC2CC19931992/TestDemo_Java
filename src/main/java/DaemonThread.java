/**
 * 守护线程
 *
 * @author tc
 * @date 2021/1/11
 */
public class DaemonThread {
    public static void main(String[] args) {
        You you = new You();
        God god = new God();
        Thread thread = new Thread(god);
        //默认为false代表用户线程，true为守护线程
        thread.setDaemon(true);
        thread.start();
        new Thread(you).start();
    }


}

class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 365; i++) {
            System.out.println("开始");
        }
        System.out.println("结束==========");
    }
}
class God implements Runnable{
    @Override
    public void run() {
        Test test =new Test();
        Thread thread1 = new Thread(test);
        //默认为false代表用户线程，true为守护线程
        thread1.setDaemon(true);
        thread1.start();
        while (true){
            System.out.println("++++++++++++");
        }
    }
}

class Test implements Runnable{
    @Override
    public void run() {
        System.out.println("Test开始==");
//        for (int i = 0; i < 10; i++){
//            System.out.println("========");
//        }
        System.out.println("Test结束==");
        while (true){
            System.out.println("========");
        }

    }
}
