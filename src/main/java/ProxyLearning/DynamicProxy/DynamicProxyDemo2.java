package ProxyLearning.DynamicProxy;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.sql.DriverManager;

/**
 * CGLib代理 https://www.jianshu.com/p/829e93528d56 由Spring内置提供的
 * https://www.jianshu.com/p/9a61af393e41?from=timeline&isappinstalled=0
 *
 * @author tc
 * @date 2021/1/13
 */
public class DynamicProxyDemo2 {
    public static void main(String[] args) {
        //设置cglib生成的源码目录 【此处应该是调试用到的】
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\Java-study\\");
        //Enhancer是CGLIB的字节码增强器，可以很方便的对类进行拓展
        Enhancer enhancer = new Enhancer();
        //继承被代理类
        enhancer.setSuperclass(HelloServiceImpl.class);
        //设置回调
        enhancer.setCallback(new HelloMethodInterceptor());
        //设置代理类对象
        HelloServiceImpl helloService = (HelloServiceImpl) enhancer.create();
        //在调用代理类中方法时会被我们实现的方法拦截器进行拦截
        helloService.sayHello();
        helloService.sayBey();
    }
}

//实现MethodInterceptor接口生成方法拦截器
class HelloMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib before");
        Object result = methodProxy.invokeSuper(obj, objects);
        System.out.println("cglib after");
        return result;
    }
}

//被代理类
class HelloServiceImpl {
    public final void sayHello(){
        System.out.println("Hello tc");
    }

    public final void sayBey(){
        System.out.println("Bye tc");
    }
}