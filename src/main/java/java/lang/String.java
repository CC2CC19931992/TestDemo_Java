package java.lang;

/**
 * 会报错。双亲委派机制：当一个类加载器去加载类时先尝试让父类加载器去加载，如果父类加载器加载不了再尝试自身加载
 *
 * @author tc
 * @date 2021/3/9
 */
public class String {

    public String toString() {
        return "hello";
    }

    public static void main(String[] args){
        String s= new String();
        System.out.println(s.getClass().getClassLoader());
    }
}