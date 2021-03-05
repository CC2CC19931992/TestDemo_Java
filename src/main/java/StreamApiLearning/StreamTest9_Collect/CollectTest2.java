package StreamApiLearning.StreamTest9_Collect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分组
 *
 * @author tc
 * @date 2021/3/1
 */
public class CollectTest2 {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    /**
     * 按字段分组
     * 按条件分组
     *
     * @param args
     */
    public static void main(String[] args) {
        // GROUP BY address
        Map<String, List<Person>> groupingByAddress = list.stream().collect(Collectors.groupingBy(Person::getAddress));
        System.out.println(groupingByAddress);

        // GROUP BY address, age
        Map<String, Map<Integer, List<Person>>> doubleGroupingBy = list.stream()
                .collect(Collectors.groupingBy(Person::getAddress, Collectors.groupingBy(Person::getAge)));
        System.out.println(doubleGroupingBy);

        // 简单来说，就是collect(groupingBy(xx)) 扩展为 collect(groupingBy(xx, groupingBy(yy)))，嵌套分组

        // 解决了按字段分组、按多个字段分组，我们再考虑一个问题：有时我们分组的条件不是某个字段，而是某个字段是否满足xx条件
        // 比如 年龄大于等于18的是成年人，小于18的是未成年人
        Map<Boolean, List<Person>> adultsAndTeenagers = list.stream().collect(Collectors.partitioningBy(person -> person.getAge() >= 18));
        System.out.println(adultsAndTeenagers);
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
