package FunctionalInterfaceTest;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/23
 */
public class FunctionalInterfaceTest01 {
    public static void main(String[] args) {
        // Ambiguous method call
        //以下注释的这句会编译不通过 接口级别的推断冲突 interface Param1和interface Param2是一样的
        // 【两个接口的出入参都是一样，无入参 无出参】 编译器推断不了
        //lambdaMethod(() -> System.out.println("test"));
    }

    /**
     * 方法重载
     *
     * @param param1
     */
    public static void lambdaMethod(Param1 param1) {
        param1.print();
    }

    /**
     * 方法重载
     *
     * @param param2
     */
    public static void lambdaMethod(Param2 param2) {
        param2.print();
    }

    /**
     * 函数式接口1
     */
    interface Param1 {
        void print();
    }

    /**
     * 函数式接口2
     */
    interface Param2 {
        void print();
    }
}
