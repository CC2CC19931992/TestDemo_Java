package ThreadLocalTest.ThreadLocalUtil3;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/3/4
 */
public class ThreadLocalMapTest {
    public static void main(String[] args) {

        ThreadLocalMap.put("mainKey", "mainValue");

        new Thread(()->{
            ThreadLocalMap.put("threadKey", "threadValue");

            System.out.println("get main value in thread:" + ThreadLocalMap.get("mainKey"));
            System.out.println("get thread value in thread:" + ThreadLocalMap.get("threadKey"));
        }).start();

        System.out.println("get thread value in main:" + ThreadLocalMap.get("threadKey"));
        System.out.println("get main value in main:" + ThreadLocalMap.get("mainKey"));

    }

}
