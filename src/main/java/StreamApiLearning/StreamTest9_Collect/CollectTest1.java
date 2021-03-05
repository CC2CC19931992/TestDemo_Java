package StreamApiLearning.StreamTest9_Collect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/3/1
 */
public class CollectTest1 {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    /**
     * 演示用collect()方法实现聚合操作，对标max()、min()、count()
     * @param args
     */
    public static void main(String[] args) {
        // 方式1：匿名对象
        Optional<Person> max1 = list.stream().collect(Collectors.maxBy(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getAge() - p2.getAge();
            }
        }));
        System.out.println(max1.orElse(null));

        // 方式2：Lambda
        Optional<Person> max2 = list.stream().collect(Collectors.maxBy((p1, p2) -> p1.getAge() - p2.getAge()));
        System.out.println(max2.orElse(null));

        // 方式3：方法引用
        Optional<Person> max3 = list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)));
        System.out.println(max3.orElse(null));

        // 方式4：IDEA建议直接使用 max()，不要用 collect(Collector)
        Optional<Person> max4 = list.stream().max(Comparator.comparingInt(Person::getAge));
        System.out.println(max4.orElse(null));

        // 特别是方式3和方式4，可以看做collect()聚合和max()聚合的对比

        // 剩下的minBy和counting

        Optional<Person> min1 = list.stream().collect(Collectors.minBy(Comparator.comparingInt(Person::getAge)));
        Optional<Person> min2 = list.stream().min(Comparator.comparingInt(Person::getAge));

        Long count1 = list.stream().collect(Collectors.counting());
        Long count2 = list.stream().count();
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
