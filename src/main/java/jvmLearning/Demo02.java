package jvmLearning;

import java.util.Random;

/**
 * OOM实例
 *
 * 配置jvm参数-Xms8m -Xmx8m -XX:+PrintGCDetails
 * 查看辣鸡回收
 * @author tc
 * @date 2021/3/30
 */
public class Demo02 {
    /**
     * 打印出来的记录如下：PSYoungGen: 1536K->509K(2048K) 1536K表示回收前新生代的内存占用 509K表示回收后的新生代内存占用  2048K表示新生代总内存
     * 同理老年代也是如此  ParOldGen: 5122K->2333K(5632K) 5122K表示回收前老年代的内存占用 2333K表示回收后的老年代内存占用 5632K表示老年代总内存
     * 最终发现是因为老年代内存满了 导致内存溢出错误 ParOldGen: 3140K->3122K(5632K) 回收不动了 再过来 内存就溢出了
     *
     * [GC (Allocation Failure) [PSYoungGen: 1536K->509K(2048K)] 1536K->861K(7680K), 0.0019672 secs] [Times: user=0.05 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 1947K->509K(2048K)] 2299K->1327K(7680K), 0.0038903 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 2045K->493K(2048K)] 2863K->1602K(7680K), 0.0007693 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 2029K->493K(2048K)] 3138K->1874K(7680K), 0.0006913 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 1930K->493K(2048K)] 3311K->2105K(7680K), 0.0006636 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 1983K->493K(2048K)] 3594K->2597K(7680K), 0.0006703 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 1793K->509K(2048K)] 3897K->3606K(7680K), 0.0007186 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 1809K->96K(2048K)] 6176K->5218K(7680K), 0.0005071 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Ergonomics) [PSYoungGen: 96K->0K(2048K)] [ParOldGen: 5122K->2333K(5632K)] 5218K->2333K(7680K), [Metaspace: 3406K->3406K(1056768K)], 0.0041382 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 680K->0K(2048K)] 4284K->3604K(7680K), 0.0010600 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] 3604K->3604K(7680K), 0.0006807 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] [ParOldGen: 3604K->3140K(5632K)] 3604K->3140K(7680K), [Metaspace: 3407K->3407K(1056768K)], 0.0078785 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     * [GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] 3140K->3140K(7680K), 0.0002848 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] [ParOldGen: 3140K->3122K(5632K)] 3140K->3122K(7680K), [Metaspace: 3407K->3407K(1056768K)], 0.0080293 secs] [Times: user=0.05 sys=0.00, real=0.01 secs]
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3332)
     * 	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
     * 	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:674)
     * 	at java.lang.StringBuilder.append(StringBuilder.java:208)
     * 	at jvmLearning.Demo02.main(Demo02.java:17)
     * Heap
     *  PSYoungGen      total 2048K, used 76K [0x00000000ffd80000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 1536K, 4% used [0x00000000ffd80000,0x00000000ffd93170,0x00000000fff00000)
     *   from space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
     *   to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
     *  ParOldGen       total 5632K, used 3122K [0x00000000ff800000, 0x00000000ffd80000, 0x00000000ffd80000)
     *   object space 5632K, 55% used [0x00000000ff800000,0x00000000ffb0ca68,0x00000000ffd80000)
     *  Metaspace       used 3445K, capacity 4556K, committed 4864K, reserved 1056768K
     *   class space    used 366K, capacity 392K, committed 512K, reserved 1048576K
     */
    public static void main(String[] args) {
        String str = "tc";
        while (true){
            str+=str + new Random().nextInt(777777777)+new Random().nextInt(888888888);
        }
    }
}
