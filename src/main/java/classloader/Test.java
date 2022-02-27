package classloader;

import com.google.common.collect.Sets;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于被我们自己的类加载器加载
 * 先编译下工程，将Test.class拷贝到swap文件夹下。
 * 运行main方法，可观察到控制台一直输出“当前版本是1哦”。
 * 修改Test#pringtVersion方法的源代码，将输出的内容改为"当前版本是2哦"，
 * 然后编译工程，将新的Test.class拷贝到swap文件件下，并替换之前的Test.class。
 *
 * @author tc
 * @date 2021/3/9
 */
public class Test {
    public void printVersion(){
        System.out.println("当前版本是3哦");
    }

    public static void main(String[] args) {
        Integer

        //创建一个2s执行一次的定时任务
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String swapPath = MyClassLoader.class.getResource("").getPath() + "swap/";
                String className = "classloader.Test";

                //每次都实例化一个ClassLoader，这里传入swap路径，和需要特殊加载的类名
                MyClassLoader myClassLoader = new MyClassLoader(swapPath, Sets.newHashSet(className));
                try {
                    //使用自定义的ClassLoader加载类，并调用printVersion方法。
                    Object o = myClassLoader.loadClass(className).newInstance();
                    // 如果下面这段代码用强转的方式去调用方法
                    // 如：Test test = (Test)o;
                    //    o.printVersion();
                    // Test.class会隐性的被加载当前类的ClassLoader加载，
                    // 当前Main方法默认的ClassLoader为AppClassLoader，而不是我们自定义的MyClassLoader。
                    // 这样导致的结果会报错 会抛出ClassCastException，因为一个类，
                    // 就算包路径完全一致，但是加载他们的ClassLoader不一样，那么这两个类也会被认为是两个不同的类。
                    o.getClass().getMethod("printVersion").invoke(o);
                } catch (InstantiationException |
                        IllegalAccessException |
                        ClassNotFoundException |
                        NoSuchMethodException |
                        InvocationTargetException ignored) {
                }
            }
        }, 0,2000);
    }
}
