package StreamApiLearning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * collect 2
 * 有一个小坑需要注意
 * @author tc
 * @date 2021/2/25
 */
public class StreamTest6 {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("iron", 17, "宁波", 888.8));
        list.add(new Person("iron", 18, "上海", 888.8));
    }

    public static void main(String[] args) {
        //下面这段代码会报错 Duplicate key，因为有重复Key[Map数据结构是不允许有重复key的]
//        Map<String, Person> nameToPersonMap = list.stream().collect(Collectors.toMap(Person::getName, person -> person));
//        System.out.println(nameToPersonMap);

        //这一段不会报错 原因是用了toMap的重载方法，将有重复key的合并了起来了。当有重复Key的，合并为前一个Key
        //就要提前定好key的保留策略：key覆盖还是key丢弃？
        //覆盖就是取重复key数据的最后一条，丢弃就是取第一条 后面重复的就丢弃
        //下面的lambda表达式是实现的BinaryOperator接口里的唯一的一个抽象方法。
        //我们点开BinaryOperator接口[小技巧：idea里可以直接点lambda表达式里的"->"进到相关的接口里]
        //找不到抽象方法，急！急！急！
        //抬头发现这个接口 extends BiFunction。BiFunction,果然是这个逼，里面有个抽象方法  R apply(T t, U u);
        //破案了 这就是为啥这个lambda表达式的两个参数(preKey, lastKey)的由来，所以不能搞小三[第三个参数]了哦
        Map<String, Person> nameToPersonMap1 = list.stream()
                .collect(Collectors.toMap(Person::getName, person -> person, (preKey, lastKey) -> lastKey));
        System.out.println(nameToPersonMap1);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Person {
        private String name;
        private Integer age;
        private String address;
        private Double salary;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
