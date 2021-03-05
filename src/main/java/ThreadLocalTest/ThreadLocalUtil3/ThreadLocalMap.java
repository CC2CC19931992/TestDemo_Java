package ThreadLocalTest.ThreadLocalUtil3;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal封装工具类 3.0
 *
 * @author tc
 * @date 2021/3/4
 */
public class ThreadLocalMap extends ThreadLocal<Map<String, Object>> {

    private ThreadLocalMap() {
    }

    private final static ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new ThreadLocalMap();

    /**
     * 根据key获取value
     * 比如key为USER_INFO，则返回"{'name':'bravo', 'age':18}"
     * {
     * ...THREAD_CONTEXT: {
     * ........."USER_INFO":"{'name':'bravo', 'age':18}",
     * ........."SCORE":"{'Math':99, 'English': 97}"
     * ...}
     * }
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        // getContextMap()表示要先获取THREAD_CONTEXT的value，也就是Map<String, Object>。然后再从Map<String, Object>中根据key获取
        return THREAD_CONTEXT.get().get(key);
    }

    /**
     * put操作，原理同上
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        THREAD_CONTEXT.get().put(key, value);
    }

    /**
     * 清除map里的某个值
     * 比如把
     * {
     * ...THREAD_CONTEXT: {
     * ........."USER_INFO":"{'name':'bravo', 'age':18}",
     * ........."SCORE":"{'Math':99, 'English': 97}"
     * ...}
     * }
     * 变成
     * {
     * ...THREAD_CONTEXT: {
     * ........."SCORE":"{'Math':99, 'English': 97}"
     * ...}
     * }
     *
     * @param key
     * @return
     */
    public static Object remove(String key) {
        return THREAD_CONTEXT.get().remove(key);
    }

    /**
     * 清除整个Map<String, Object>
     * 比如把
     * {
     * ...THREAD_CONTEXT: {
     * ........."USER_INFO":"{'name':'bravo', 'age':18}",
     * ........."SCORE":"{'Math':99, 'English': 97}"
     * ...}
     * }
     * 变成
     * {
     * ...THREAD_CONTEXT: {}
     * }
     */
    public static void clear() {
        THREAD_CONTEXT.get().clear();
    }

    /**
     * 从ThreadLocalMap中清除当前ThreadLocal存储的内容
     * 比如把
     * {
     * ...THREAD_CONTEXT: {
     * ........."USER_INFO":"{'name':'bravo', 'age':18}",
     * ........."SCORE":"{'Math':99, 'English': 97}"
     * ...}
     * }
     * 变成
     * {
     * }
     */
    public static void clearAll() {
        THREAD_CONTEXT.remove();
    }

    @Override
    protected Map<String, Object> initialValue() {
        return new HashMap<String, Object>(8) {

            private static final long serialVersionUID = 3637958959138295593L;

            @Override
            public Object put(String key, Object value) {
                return super.put(key, value);
            }
        };
    }

}
