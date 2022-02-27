package StreamApiLearning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 去重：distinct
 * 参考其他的去重方法：https://www.jianshu.com/p/32daa76ea23f
 *
 * @author tc
 * @date 2021/3/1
 */
public class StreamTest8 {
    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9,0.0));
        list.add(new Person("am", 19, "温州", 777.7,0.0));
        list.add(new Person("iron", 21, "杭州", 888.8,0.0));
        list.add(new Person("man", 17, "宁波", 888.8,0.0));

    }

    public static void main(String[] args) {
        long count = list.stream().map(Person::getAddress).distinct().count();
        Map<String, List<Person>> groupingByAddress = list.stream().collect(Collectors.groupingBy(Person::getAddress));
        Iterator<Map.Entry<String, List<Person>>> itGroupingByRoomType = groupingByAddress.entrySet().iterator();
        while (itGroupingByRoomType.hasNext()){
            Map.Entry<String, List<Person>> entry = itGroupingByRoomType.next();
            Iterator it = entry.getValue().iterator();
            while (it.hasNext()){
                Person temp = (Person)it.next();
                temp.setBeforeSalary(temp.getBeforeSalary()+1);
            }
        }
        Collections.sort(list,(o, p) -> p.getAge().compareTo(o.getAge()));
        //list =list.stream().sorted((o, p) -> p.getAge().compareTo(o.getAge())).collect(Collectors.toList());
        System.out.println(list);
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
        private Double beforeSalary;
    }
}
