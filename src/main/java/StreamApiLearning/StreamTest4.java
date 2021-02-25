package StreamApiLearning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * limit/skip
 * 所谓的skip(N)就是跳过前面N个元素，limit(N)就是只取N个元素。
 *
 * @author tc
 * @date 2021/2/25
 */
public class StreamTest4 {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    public static void main(String[] args) {
        List<String> result = list.stream()
                .filter(person -> person.getAge() > 17)
                // peek()先不用管，它不会影响整个流程，就是打印看看filter操作后还剩什么元素
                .peek(person -> System.out.println(person.getName()))
                .skip(1)
                .limit(2)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(result);
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
