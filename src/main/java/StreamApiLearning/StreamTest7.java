package StreamApiLearning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 聚合:max/min/count
 *
 * @author tc
 * @date 2021/3/1
 */
public class StreamTest7 {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    public static void main(String[] args) {
        // 匿名内部类的方式，实现Comparator，明确按什么规则比较（所谓最大，必然是在某种规则下的最值）
        Optional<Integer> maxAge = list.stream().map(Person::getAge).max(new Comparator<Integer>() {
            @Override
            public int compare(Integer age1, Integer age2) {
                return age1 - age2;
            }
        });
        System.out.println(maxAge.orElse(0));

        Optional<Integer> max = list.stream().map(Person::getAge).max(Integer::compareTo);
        System.out.println(max.orElse(0));

        long count = list.stream().filter(person -> person.getAge() > 18).count();
        System.out.println(count);
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
