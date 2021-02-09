package ProxyLearning;

/**
 * 静态代理
 *
 * @author tc
 * @date 2021/1/12
 */
public class StaticProxy {
    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.sayHello();
    }
}

//接口
interface HelloInterface {
    void sayHello();
}

//被代理类【目标类】
class Hello implements HelloInterface{
    @Override
    public void sayHello() {
        System.out.println("Hello tc!");
    }
}

class HelloProxy implements HelloInterface{
    HelloInterface helloInterface = new Hello();
    @Override
    public void sayHello() {
        System.out.println("Before invoke sayHello" );
        helloInterface.sayHello();
        System.out.println("After invoke sayHello");
    }
}