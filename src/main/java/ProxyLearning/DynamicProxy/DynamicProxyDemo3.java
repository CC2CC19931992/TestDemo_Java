package ProxyLearning.DynamicProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @author tc
 * @date 2021/1/21
 */
public class DynamicProxyDemo3 {
    public static void main(String[] args) throws Throwable {
        CalculatorImpl target = new CalculatorImpl("aaaa");
        //传入目标对象
        //目的：1.根据它实现的接口生成代理对象 2.代理对象调用目标对象方法
        Calculator calculatorProxy = (Calculator) getProxy(target);
        calculatorProxy.add(1, 2);
        calculatorProxy.subtract(2, 1);
    }

    private static Object getProxy(final Object target) throws Exception {
        //这里创建对象会报错 原因是因为CalculatorImpl没有无参构造
        // CalculatorImpl sasd = (CalculatorImpl)Class.forName("ProxyLearning.DynamicProxy.CalculatorImpl").newInstance();
        //参数1：随便找个类加载器给它， 参数2：目标对象实现的接口，让代理对象实现相同接口
        //得到的是代理对象的类【匿名类】
        Class proxyClazz = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
        //得到代理类的构造器 这个构造器的参数是 InvocationHandler类型
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        Object proxy = constructor.newInstance(new InvocationHandler() {
            //这里InvocationHandler是接口 用这个new对象的时候是创建的某匿名类的对象，里面必须实现InvocationHandler接口的方法
            //注意 这个实现InvocationHandler接口的对象所属匿名类 和 上面的代理类proxyClazz不是同一个类
            @Override
            public Object invoke(Object proxy1, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName() + "方法开始执行...");
                Object result = method.invoke(target, args);
                System.out.println(result);
                System.out.println(method.getName() + "方法执行结束...");
                return result;
            }
        });
        return proxy;

    }
}

interface Calculator{
    int add(int a,int b);
    int subtract(int a,int b);
}
interface CastClass{

}

class CalculatorImpl implements Calculator{
    public String tem;

    public CalculatorImpl(String c){
        tem=c;
    }

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int subtract(int a, int b) {
        return a-b;
    }
}
