package ThreadLocalTest;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal封装工具类 1.0
 *
 * @author tc
 * @date 2021/3/3
 */
public class ThreadLocalUtil1 {
    private ThreadLocalUtil1() {
    }

    /**
     * ThreadLocal是紫霞仙子，至尊宝是Thread
     * ThreadLocal的泛型规定了紫霞仙子劈开至尊宝时，能给他心里塞的东西的类型。
     * <p>
     * 比如
     * 将ThreadLocal泛型指定为String，那么造了一个ThreadLocalMap后，这个map只能存 threadLocal:"这是字符串" 这样的键值对
     * 将ThreadLocal泛型指定为Integer，那么造了一个ThreadLocalMap后，这个map只能存 threadLocal:1111111111 这样的键值对
     *
     * 由于单纯的value会发生值覆盖，所以我们使用Map<String, Object>作为value
     */
    private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new ThreadLocal<>();


    /**
     * 存入线程变量
     *
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        /**
         * 至尊宝(一个Thread)经过这段代码，遇到了紫霞(THREAD_CONTEXT)。大家可以点进get()看看，内部操作是:
         * 1.把至尊宝的心取出来（从Thread中取出ThreadLocalMap）
         *
         * ThreadLocalMap的构造类似于这样
         * {
         * ...THREAD_CONTEXT: {
         * ........."USER_INFO":"{'name':'bravo', 'age':18}",
         * ........."SCORE":"{'Math':99, 'English': 97}"
         * ......}
         * }
         *
         * 2.ThreadLocalMap.Entry e = map.getEntry(this); 把自己(THREAD_CONTEXT)作为key，取出属于自己的value，此时value是一个Map<String, Object>。
         * 3.所以最终THREAD_CONTEXT.get()返回的Map<String, Object> map
         *
         */
        Map<String, Object> map = THREAD_CONTEXT.get();
        // 第一次从ThreadLocalMap中根据threadLocal取出的value可能是null
        if (map == null) {
            map = new HashMap<>();
            // 把map作为value放进去
            THREAD_CONTEXT.set(map);
        }
        /**
         * 假设本次存的是 USER_INFO:{"name":"bravo", "age":18}
         * 此时ThreadLocalMap中的结构是
         * {
         * ...THREAD_CONTEXT: {
         * ........."USER_INFO":"{'name':'bravo', 'age':18}",
         * ......}
         * }
         *
         */
        map.put(key, object);
    }

    /**
     * 取出线程变量
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        // 先获取Map
        Map<String, Object> map = THREAD_CONTEXT.get();
        // 从Map中得到USER_INFO
        return map != null ? map.get(key) : null;
    }

    /**
     * 移除当前线程的指定变量
     * 比如把
     * {
     * ...THREAD_CONTEXT: {
     * ........."USER_INFO":"{'name':'bravo', 'age':18}",
     * ........."SCORE":"{'Math':99, 'English': 97}"
     * ......}
     * }
     * 变成
     * {
     * ...THREAD_CONTEXT: {
     * ........."SCORE":"{'Math':99, 'English': 97}"
     * ......}
     * }
     * 并不是移除所有，而是只移除USER_INFO
     *
     * @param key
     */
    public static void remove(String key) {
        Map<String, Object> map = THREAD_CONTEXT.get();
        map.remove(key);
    }

    /**
     * 移除当前线程的所有变量
     * 比如把
     * {
     * ...THREAD_CONTEXT: {
     * ........."USER_INFO":"{'name':'bravo', 'age':18}",
     * ........."SCORE":"{'Math':99, 'English': 97}"
     * ......}
     * }
     * 变成
     * {
     * }
     */
    public static void clear() {
        THREAD_CONTEXT.remove();
    }
}
