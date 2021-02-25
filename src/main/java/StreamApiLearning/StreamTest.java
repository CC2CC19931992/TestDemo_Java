package StreamApiLearning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * collect()、Collector、Collectors三者的关系是：
 * collect()通过传入不同的Collector对象来明确如何收集元素，比如收集成List还是Set还是拼接字符串？
 * 而通常我们不需要自己实现Collector接口，只需要通过Collectors获取。
 *
 * @author tc
 * @date 2021/2/23
 */
public class StreamTest {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("甲", 18, "杭州", 999.9));
        list.add(new Person("乙", 19, "温州", 777.7));
        list.add(new Person("丙", 21, "杭州", 888.8));
        list.add(new Person("丁", 17, "宁波", 888.8));

    }

    public static void main(String[] args) {
        List<Person> result = list.stream()
                .filter(person -> person.getAge() > 20)
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
