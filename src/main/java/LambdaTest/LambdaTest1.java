package LambdaTest;

import java.util.Comparator;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/21
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abcd";

        //用匿名类
        int i = compareString(str1, str2, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
    }

    public static int compareString(String str1, String str2, Comparator<String> comparator) {
        return comparator.compare(str1, str2);
    }
}
