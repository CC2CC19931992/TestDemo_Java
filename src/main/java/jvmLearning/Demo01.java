package jvmLearning;

import org.omg.SendingContext.RunTime;

/**
 * JVM堆内存查看和设置
 *
 * @author tc
 * @date 2021/3/30
 */
public class Demo01 {
    public static void main(String[] args) {
        //返回JVM试图使用的最大内存(返回的是字节数byte)
        long max= Runtime.getRuntime().maxMemory();
        //返回JVM的总内存
        long total = Runtime.getRuntime().totalMemory();

        System.out.println("max="+max+"字节\t"+(max/(double)1024/1024)+"MB");
        System.out.println("total="+total+"字节\t"+(total/(double)1024/1024)+"MB");

        //默认情况下：分配的总内存是电脑内存的1/4，而初始化的内存是电脑内存的1/64
        //发生OOM后的操作：1.尝试扩大堆内存，看结果 2.分析堆内存看下哪个地方出现了问题
        //修改堆内存如下
        //-Xms1024m -Xmx1024m -XX:+PrintGCDetails
        /**
         * max=1029177344字节	981.5MB
         * total=1029177344字节	981.5MB
         * Heap
         *  PSYoungGen      total 305664K, used 26224K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
         *   eden space 262144K, 10% used [0x00000000eab00000,0x00000000ec49c028,0x00000000fab00000)
         *   from space 43520K, 0% used [0x00000000fd580000,0x00000000fd580000,0x0000000100000000)
         *   to   space 43520K, 0% used [0x00000000fab00000,0x00000000fab00000,0x00000000fd580000)
         *  ParOldGen       total 699392K, used 0K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
         *   object space 699392K, 0% used [0x00000000c0000000,0x00000000c0000000,0x00000000eab00000)
         *  Metaspace       used 3453K, capacity 4556K, committed 4864K, reserved 1056768K
         *   class space    used 368K, capacity 392K, committed 512K, reserved 1048576K
         */

        //发现 PSYoungGen(新生代)+ParOldGen(老年代) = 1,005,056K = 981.5M
        //而看到元空间也有 内存占用，但是发现占用的不是JVM的内存。推断元空间要么是
    }
}
