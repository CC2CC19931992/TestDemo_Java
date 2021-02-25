package StreamApiLearning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream#sorted()
 *
 * @author tc
 * @date 2021/2/25
 */
public class StreamTest3 {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    public static void main(String[] args) {
        // 直接链式操作
        List<String> nameList = list.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(nameList);

        // 我想按姓名长度排序
        List<String> sortedNameList = list.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedNameList);

        // 你：我擦，说好的排序呢？

        // Stream：别扯淡，你告诉我排序规则了吗？（默认自然排序）

        // 明白了，那就按照长度倒序吧（注意细节啊，str2-str1才是倒序）
        List<String> realSortedNameList = list.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .sorted((str1, str2) -> str2.length() - str1.length())
                .collect(Collectors.toList());
        System.out.println(realSortedNameList);

        // 优化一下：我记得在之前那张很大的思维导图上看到过，sorted()有重载方法，是sorted(Comparator)
        // 上面Lambda其实就是调用sorted(Comparator)，用Lambda给Comparator接口赋值
        // 但Comparator还供了一些方法，能返回Comparator实例
        List<String> optimizeNameList = list.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(optimizeNameList);

        // 又是一样的套路，Comparator.reverseOrder()返回的其实是一个Comparator！！

        // 但上面的有点投机取巧，来个正常点的，使用Comparator.comparing()
        List<String> result1 = list.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .sorted(Comparator.comparing(t -> t, (str1, str2) -> str2.length() - str1.length()))
                .collect(Collectors.toList());
        System.out.println(result1);

        // 我去，更麻烦了！！
        // 不急，我们先来了解上面案例中Comparator的两个参数
        // 第一个是Function映射，就是指定要排序的字段，由于经过上一步map操作，已经是name了，就不需要映射了，所以是t->t
        // 第二个是比较规则

        // 我们把map和sorted调换一下顺序，看起来就不那么别扭了
        List<String> result2 = list.stream()
                .filter(person -> person.getAge() > 18)
                .sorted(Comparator.comparing(Person::getName, String::compareTo).reversed())
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(result2);

        // 为什么Comparator.comparing().reversed()可以链式调用呢？
        // 上面说了哦，因为Comparator.comparing()返回的还是Comparator对象~
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
