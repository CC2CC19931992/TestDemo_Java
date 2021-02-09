package TypeCastTest;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/29
 */
public class TypeTest1 {
    public static void  main(String[] args){
        Object objClass=new Object();
        //这里编译不会报错 但是运行的时候会报错。类型无法转换ClassCastException
//        InterfaceA a =(InterfaceA)objClass;
//        InterfaceB b =(InterfaceB)objClass;

        //这句也会报错，父类不能强转为子类
       // ClassC c = (ClassC) (new ClassA());
       // ClassC c = (ClassC) (new ClassA());

        //子类可以转换为父类
        ClassA d =(ClassA) (new ClassC());
        System.out.println("==============");
    }
}

interface InterfaceA{

}

interface InterfaceB{

}

class ClassA{

}

class ClassB{

}

class ClassC extends ClassA{

}
