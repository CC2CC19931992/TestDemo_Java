package jvmLearning;

import java.util.ArrayList;

/**
 * Dump文件抓取
 * jvm参数设置：-Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError 堆内存发生溢出错误时抓取Dump文件
 *
 * @author tc
 * @date 2021/3/30
 */
public class Demo03 {
    byte[] array = new byte[1*1024*1024];
    public static void main(String [] args){
        ArrayList<Demo03> list = new ArrayList<>();
        int count = 0;
        try {
            while (true){
                list.add(new Demo03());
                count = count+1;
            }
        }catch (Error e){
            System.out.println("count:"+count);
            e.printStackTrace();
        }

    }
}
