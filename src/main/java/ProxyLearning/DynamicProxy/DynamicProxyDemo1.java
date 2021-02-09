package ProxyLearning.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * 动态代理【JDK代理】 https://www.zhihu.com/question/20794107
 *
 * @author tc
 * @date 2021/1/12
 */
public class DynamicProxyDemo1 {
    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //接口是无法创建对象的，但是可以申明对象。这里就是用接口神明了对象
        HelloInterface1 hello = new Hello1("");

        InvocationHandler handler = new ProxyHandler(hello);

        HelloInterface1 proxyHello = (HelloInterface1) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), handler);

        proxyHello.sayHello();
    }

}

//接口
interface HelloInterface1 {
    void sayHello();
}

//被代理类【目标类或委托类】
class Hello1 implements HelloInterface1{
    @Override
    public void sayHello() {
        System.out.println("Hello tc!");
    }

    public Hello1(String a){

    }

    @Override
    public String toString(){
        return "Hello1 toString";
    }
}

//代理类
class ProxyHandler implements InvocationHandler {
    private Object object;
    public ProxyHandler(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(new Date()+"||Before invoke,"  + "methodName:"+method.getName());
        method.invoke(object, args);
        System.out.println(new Date()+"||After invoke " + "methodName:"+method.getName());
        return null;
    }

    @Override
    public String toString(){
        return "ProxyHandler toString";
    }
}

