package annotationtest.annotation03_MyJPA;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/21
 */

public class Test{
    public static void main(String [] args){
        new B();
    }
}

class A<T>{
    public A(){
          /*
                我想在这里获得子类B、C传递的实际类型参数的Class对象
                class java.String/class java.lang.Integer
          */
//          Class<T> tClass = T.class; 这样是行不通的
//          System.out.println(tClass);

        //this是谁？A还是B？
        Class clazz = this.getClass();
        System.out.println(clazz.getName());
        //得到父类(参数化类型)类型，这一步相当于拿到：A<String>,父类及泛型
        Type genericSuperClass=clazz.getGenericSuperclass();
        System.out.println(genericSuperClass);
        System.out.println(genericSuperClass.getClass());
        //将得到的ParameterizedTypeImpl类型的 genericSuperClass 强转为 接口类型ParameterizedType
        ParameterizedType superclass = (ParameterizedType) genericSuperClass;
        //强转以后发现有个getActualTypeArguments方法,返回值是所有泛型类型的Class对象
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        //由于A类只有一个泛型，所以直接actualTypeArguments[0]得到子类传来的T的Class对象
        Class actualTypeArgument = (Class) actualTypeArguments[0];
        System.out.println(actualTypeArgument);
    }
}

class B extends A<String>{

}

class C extends A<Integer>{

}
