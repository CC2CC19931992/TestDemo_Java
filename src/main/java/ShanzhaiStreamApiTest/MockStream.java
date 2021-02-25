package ShanzhaiStreamApiTest;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/22
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
//import jdk.nashorn.internal.objects.annotations.Getter;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bravo
 * @date 2020-03-19 17:55
 */
public class MockStream {

    public static void main(String[] args) throws JsonProcessingException {

        MyList<Person> personMyList = new MyList<>();
        personMyList.add(new Person("李健", 46));
        personMyList.add(new Person("周深", 28));
        personMyList.add(new Person("张学友", 59));

        // 需求：过滤出年龄大于40的歌手的名字
        MyList<String> result = personMyList.filter(person -> person.getAge() > 40).map(Person::getName);
        prettyPrint(result.getList());

        System.out.println("\n---------------------------------------------\n");

        // 对比真正的Stream API
        List<Person> list = new ArrayList<>();
        list.add(new Person("李健", 46));
        list.add(new Person("周深", 28));
        list.add(new Person("张学友", 59));

        List<String> collect = list
                .stream()                               // 真正的Stream API需要先转成stream流
                .filter(person -> person.getAge() > 40) // 过滤出年纪大于40的歌手
                .map(Person::getName)                   // 拿到他们的名字
                .collect(Collectors.toList());          // 整理成List<String>

        prettyPrint(collect);
    }


    /**
     * 按JSON格式输出
     *
     * @param obj
     * @throws JsonProcessingException
     */
    private static void prettyPrint(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        System.out.println(s);
    }


}


@Data
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
}

@Getter
class MyList<T> {
    private List<T> list = new ArrayList<>();

    public List<T> getList() {
        return list;
    }

    public boolean add(T t) {
        return list.add(t);
    }

    /**
     * 给MyList传递具体的判断规则，然后MyList把内部现有符合判断（true）的元素集返回
     * @param predicate
     * @return
     */
    public MyList<T> filter(Predicate<T> predicate){
        MyList<T> filteredList = new MyList<>();

        for (T t : list) {
            if (predicate.test(t)) {
                // 收集判断为true的元素
                filteredList.add(t);
            }
        }

        return filteredList;
    }

    /**
     * 把MyList中的List<T>转为List<R>
     *
     * @param mapper
     * @param <R>
     * @return
     */
    public <R> MyList<R> map(Function<T, R> mapper) {
        MyList<R> mappedList = new MyList<>();

        for (T t : list) {
            mappedList.add(mapper.apply(t));
        }

        return mappedList;
    }

}

/**
 * 定义一个Predicate接口，名字无所谓
 *
 * @param <T>
 */
@FunctionalInterface
interface Predicate<T> {
    /**
     * 定义了一个test()方法，传入任意对象，返回true or false，具体判断逻辑由子类实现
     *
     * @param t
     * @return
     */
    boolean test(T t);
}


/**
 * 定义一个Function接口，名字无所谓
 *
 * @param <E>
 * @param <R>
 */

@FunctionalInterface
interface Function<E, R> {
    /**
     * 定义一个apply()方法，接收一个E返回一个R。也就是把E映射成R
     *
     * @param e
     * @return
     */
    R apply(E e);
}
