package generic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/25
 */
public class GenericFunction {
    private static <E> Set<E> union(Set<E> s1, Set<E> s2){
        Set result = new HashSet();
        result.add(s2);
        return result;
    }

    public static void main(String[] args){
        Set<String> set1 = new HashSet<>(Arrays.asList("1","2"));
        Set<String> set2 = new HashSet<>(Arrays.asList("3","4"));

        //Set<Integer> set3 = union(set1,set2);会报错 类型推导出现问题
        Set<String> set4 = union(set1,set2);
    }
}
