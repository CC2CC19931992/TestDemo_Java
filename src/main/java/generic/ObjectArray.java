package generic;

/**
 * 泛型底层运作的模拟 当泛型完成了“编译时检查”和“编译时自动类型转换”的作用后，底层还是要多态来支持
 *
 * @author tc
 * @date 2021/1/26
 */
public class ObjectArray {
    public static void main(String[] args) {
        // ArrayList<T>底层还是Object[]数组
        Object[] objects = new Object[4];

        // 引入泛型后的两个作用：

        // 1. 在编译期把元素类型限制为指定的类型，比如ArrayList<Integer>
        objects[0] = 1;
        objects[1] = 2;

        // 1. 在编译期把元素类型限制为指定的类型，比如ArrayList<String>
        objects[2] = "3";
        objects[3] = "4";

        // 2. 编译期编译后，会根据类型参数自动转换，不用我们操心。转为Integer
        Integer zero = (Integer) objects[0];
        Integer one = (Integer) objects[1];

        // 2. 编译期编译后，会根据类型参数自动转换，不用我们操心。转为String
        String two = (String) objects[2];
        String three = (String) objects[3];

        System.out.println(zero + " " + one + " " + two + " " + three);

    }
}
