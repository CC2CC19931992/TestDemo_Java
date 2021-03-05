package StreamApiLearning.StreamTest_forEach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * forEach
 *
 * @author tc
 * @date 2021/3/1
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
        //遍历操作，接收Consumer

        //用匿名类实现
        list.stream().forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println(person);
            }
        });

        //用lambda表达式实现
        list.stream().forEach(o->System.out.println(o));

        //方法引用
        list.stream().forEach(System.out::println);
        // 简化，本质上不算同一个方法的简化
        list.forEach(System.out::println);
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
