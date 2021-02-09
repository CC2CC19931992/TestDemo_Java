package ObserverTest;

/**
 * 事件（就是包装后的被监听对象）
 *
 * @author tc
 * @date 2021/2/1
 */
public class Event {

    private Thief thief;

    public Event() {
    }

    public Event(Thief thief) {
        this.thief = thief;
    }

    public Thief getThief() {
        return thief;
    }

    public void setThief(Thief thief) {
        this.thief = thief;
    }
}
