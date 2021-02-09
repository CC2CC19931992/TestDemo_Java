package ProxyLearning.DynamicProxy1;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/29
 */
public class RealSubject implements Subject {
    @Override
    public void request(){
        System.out.println("From real subject.");
    }
}
