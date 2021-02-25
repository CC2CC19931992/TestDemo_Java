package LambdaTest;

import java.util.Comparator;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/21
 */
public class LambdaTest3 {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abcd";

        // 上面推导得出Lambda表达式与匿名类对象等价，所以我们可以把Lambda表达式赋值给Comparator接口
        Comparator<String> comparator = (String s1, String s2) -> {
            return s1.length() - s2.length();
        };
        // 调用
        int k = compareString(str1, str2, comparator);

        // 改进一下，跳过赋值这一步，直接把整个Lambda传给compareString()方法：
        compareString(str1, str2, (String s1, String s2) -> {
            return s1.length() - s2.length();
        });

        // 上面的代码虽然能运行，但是idea一直显示灰色，说有更优雅的写法。好吧，我改改。
        int x = compareString(str1, str2, (s1, s2) -> s1.length() - s2.length());

        // 不对，还是不够精简，再改改（方法引用）：
        x = compareString(str1, str2, Comparator.comparingInt(String::length));

        // 完美。
    }

    public static int compareString(String str1, String str2, Comparator<String> comparator) {
        return comparator.compare(str1, str2);
    }
}
