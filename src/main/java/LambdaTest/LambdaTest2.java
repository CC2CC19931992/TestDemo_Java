package LambdaTest;

import java.util.Comparator;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/21
 */
public class LambdaTest2 {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abcd";

        // 上面推导得出Lambda表达式与匿名类对象等价，所以我们可以把Lambda表达式赋值给Comparator接口
        Comparator<String> comparator = (String s1, String s2) -> {
            return s1.length() - s2.length();
        };
        // 调用
        int k = compareString(str1, str2, comparator);
    }

    public static int compareString(String str1, String str2, Comparator<String> comparator) {
        return comparator.compare(str1, str2);
    }
}
