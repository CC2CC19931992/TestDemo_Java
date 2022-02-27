package MethodReferenceTest;

import java.util.Comparator;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/23
 */
public class MethodReferenceTest01 {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abcd";

        // 方式1：匿名对象
        Comparator<String> comparator1 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        compareString(str1, str2, comparator1);


        // 方式2：过渡为Lambda表达式
        Comparator<String> comparator2 = (String s1, String s2) -> {
            return s1.length() - s2.length();
        };
        compareString(str1, str2, comparator2);

        // 方式2的改进版：省去赋值操作，直接把整个Lambda表达式作为参数丢进去
        compareString(str1, str2, (String s1, String s2) -> {
            return s1.length() - s2.length();
        });

        // 方式2的最终版：把变量类型和return也去掉了，因为Java可以自动推断
        compareString(str1, str2, (s1, s2) -> s1.length() - s2.length());


        // 方式3：换种比较方式，本质和方式2是一样的，不信你去看看String#compareTo()
        Comparator<String> comparator3 = (s1, s2) -> s1.compareTo(s2);


        // 方式4：IDEA提示有改进的写法，最终变成了方法引用
        compareString(str1, str2, String::compareTo);

        // 完美。

    }

    /**
     * 传递Comparator，对str1和str2进行比较
     *
     * @param str1
     * @param str2
     * @param comparator
     */
    public static void compareString(String str1, String str2, Comparator<String> comparator) {
        System.out.println(comparator.compare(str1, str2));
    }
}
