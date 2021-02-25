package StreamApiLearning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 复习Comparator
 *
 * @author tc
 * @date 2021/2/24
 */
public class StreamTest2 {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    public static void main(String[] args) {
        // JDK8之前：Collections工具类+匿名内部类。Collections类似于Arrays工具类，我经常用Arrays.asList(）
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().length()-p2.getName().length();
            }
        });

        // JDK8之前：List本身也实现了sort()
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().length()-p2.getName().length();
            }
        });

        // JDK8之后：Lambda传参给Comparator接口，其实就是实现Comparator#compare()。注意，equals()是Object的，不妨碍
        list.sort((p1,p2)->p1.getName().length()-p2.getName().length());

        // JDK8之后：使用JDK1.8为Comparator接口新增的comparing()方法
        list.sort(Comparator.comparingInt(p -> p.getName().length()));
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
