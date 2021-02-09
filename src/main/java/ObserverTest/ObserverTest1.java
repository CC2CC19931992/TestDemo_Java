package ObserverTest;

/**
 * 观察者模式
 *
 * @author tc
 * @date 2021/2/1
 */
public class ObserverTest1 {
    public static void main(String[] args) {
        // 被监听对象
        Thief thief = new Thief();

        // 监听器，直接new一个接口的匿名类对象
        ThiefListener thiefListener = new ThiefListener() {
            @Override
            public void shot(Event event) {
                System.out.println("！！！！");
            }
        };

        // 注册监听
        thief.registerListener(thiefListener);

        // 特定行为，触发监听器：内部调用listener.shot()
        thief.steal();
    }
}
