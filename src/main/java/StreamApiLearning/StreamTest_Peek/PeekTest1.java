package StreamApiLearning.StreamTest_Peek;

import java.util.stream.Stream;

/**
 * 用来观察数据的流转
 *
 * @author tc
 * @date 2021/3/2
 */
public class PeekTest1 {
    //再次分析下这里代码
    public static void main(String[] args) {
        //首先Stream.of方法返回的表达式是Arrays.stream(values)
        //抽丝剥茧一路往下看Arrays类的静态方法stream(T[] array)
        //该方法调用了自己的兄弟方法stream(T[] array, int startInclusive, int endExclusive)【上面方法的重载】
        //该方法是内部返回表达式是StreamSupport.stream(****,****)，返回ReferencePipeline.Head类型
        //ReferencePipeline.Head是继承了ReferencePipeline【老朋友嘛】
        Stream<Integer> stream = Stream.of(1, 2, 3);
        //由上面可知stream为ReferencePipeline对象。则Stream接口的peek方法的实现也是在ReferencePipeline类里能找到实现
        //peek这里的意思观察。尽管这里写了System.out.print,但是程序在这里还暂时不会输出，首先要明确元素是逐个通过的
        Stream<Integer> stream1=stream.peek(v-> System.out.print("初始值"+v+","));
        Stream<Integer> stream2= stream1.map(value -> value + 100);
        Stream<Integer> stream3=stream2.peek(v-> System.out.print("映射值"+v+","));
        stream3.forEach(o->System.out.println("最终值"+o+","));

        //元素一个个通过关卡
        //1 1先peek打印[挂起]      filter筛选>=2的，过不去   停止
        //2 2先peek打印[挂起]     filter筛选大于2的，过去   peek打印[挂起]  filter筛选>=3的，过不去  停止
        //3 3先peek打印[挂起]     filter筛选大于2的，过去   peek打印[挂起]  filter筛选>=3的，过去    forEach打印
        //4 4先peek打印[挂起]     filter筛选大于2的，过去   peek打印[挂起]  filter筛选>=3的，过去    forEach打印
        //对于peek的理解：里面的print操作不会在执行完后立刻执行。而是需要等到终止操作（比如max forEach）后才会执行打印
        Stream.of(1, 2, 3, 4)
                .peek(v -> System.out.print(v + ","))
                .filter(v -> v >= 2)
                .peek(v -> System.out.print(v + ","))
                .filter(v -> v >= 3)
                .forEach(System.out::println);;
    }
}
