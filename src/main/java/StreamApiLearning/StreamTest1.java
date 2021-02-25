package StreamApiLearning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * map/filter
 *
 * @author tc
 * @date 2021/2/24
 */
public class StreamTest1 {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    public static void main(String[] args) {
        // 我们先学中间操作

        // 1.先获取流（不用管其他乱七八糟的创建方式，记住这一个就能应付95%的场景）
        //解读：stream()方法是跳转到StreamSupport.stream(xxx,xxx)方法里
        //再从StreamSupport.stream点进去发现最终返回的是class Head<E_IN, E_OUT>类型
        //该类型继承的是ReferencePipeline<E_IN, E_OUT>类型
        //OK这里先记住
        Stream<Person> stream = list.stream();
        // 2.过滤年纪大于18岁的
        //解读：这里filter是Stream<T> filter(Predicate<? super T> predicate)抽象方法
        //而上面的返回却是Head类,此类继承了ReferencePipeline，而ReferencePipeline实现了Stream接口
        //原来是一个东西。那filter抽象方法的具体实现是在ReferencePipeline里了
        //推断的没错，在ReferencePipeline类里找到了filter的具体实现方法
        //该方法返回的类型是StatelessOp<E_IN, E_OUT>，嗯，没错这个类又继承了ReferencePipeline
        Stream<Person> filteredByAgeStream = stream.filter(person -> person.getAge() > 18);
        // 3.只要名字，不需要整个Person对象（为什么在这个案例中，filter只能用Lambda，map却可以用方法引用？）
        //解读：map是接口类型Stream<T>里的一个抽象方法
        //对了 由第二步看到了返回的类型是StatelessOp<E_IN, E_OUT>，继承了我们的老朋友ReferencePipeline
        //果不其然，我又在老朋友里找到了map的具体的实现
        //返回 class StatelessOp<E_IN, E_OUT>类。发现 这个类又又继承了这个老朋友(逼)ReferencePipeline
        Stream<String> nameStream = filteredByAgeStream.map(person -> person.getName());

        //这里是对Collectors.toList()进行类型给定。通过返回参数去推断Collectors.toList()的返回类型【泛型】
        //JDK给我提供了Collectors，可以调用Collectors提供的方法返回Collector对象
        //方法是Collector<T, ?, List<T>> toList()
        Collector<String, ?, List<String>> aas= Collectors.toList();
        // 4.现在返回值是Stream<String>，没法直接使用，帮我收集成List<String>
        //解读：collect抽象方法。嗯，也是在Stream里的。
        // 我们从第三步可以看到，返回的StatelessOp继承了ReferencePipeline
        //ok，继续，我在ReferencePipeline里又发现了collect方法的实现
        //我们依旧看返回哈：
        // return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)
        //               ? (R) container
        //               : collector.finisher().apply(container);
        //  (R) container 和 collector.finisher().apply(container) 是啥嘛？
        //从泛型方法的使用可以知道，编译器会进行类型推导
        //方法<R, A> R collect(Collector<? super T, A, R> collector)
        //由左边的返回接收类型List<String>可知  上面的R在运行的过程中会变为List<String>
        //上面一行 Collector<T, ?, List<T>> toList方法 我们也能推断出List<T>为List<String>，因而推断出T为String类型
        //所以上面那行代码就能顺利这样写： Collector<String, ?, List<String>> aas= Collectors.toList();
       List<String> nameList = nameStream.collect(aas);
        //当然 上面的两行代码等价于 List<String> nameList = nameStream.collect(Collectors.toList());
        // 现在还对collect()为什么传递Collectors.toList()感到懵逼吗？


        // 直接链式操作
        nameList = list.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .collect(Collectors.toList());
     }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        private String name;
        private Integer age;
        private String address;
        private Double salary;
    }
}
