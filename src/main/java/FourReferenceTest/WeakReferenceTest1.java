package FourReferenceTest;

import java.lang.ref.WeakReference;

/**
 * 也就是说，当一个对象被WeakReference包装后，它就产生了一个弱引用指向它。此时即使把强引用切断，
 * 仍然有弱引用连接着。但是由于弱引用的特性，这个对象会在下次被GC线程被直接回收。
 *
 * @author tc
 * @date 2021/3/3
 */
public class WeakReferenceTest1 {

    public static void main(String[] args) throws InterruptedException{

        Car car = new Car("soup");
        //用WeakReference包装Car对象
        WeakReference<Car> weakCar = new WeakReference<Car>(car);
        System.gc();
        System.out.println("强引用存在时：=====>"+weakCar.get());

        //切断原始引用，那么只有弱引用关系，GC线程一旦发现就会回收Car
        car =null;
        Thread.sleep(3*1000L);
        System.out.println("只剩弱引用但还未GC时：=====>"+weakCar.get());

        //主动GC
        System.gc();
        System.out.println("只剩弱引用且GC后：=====>"+weakCar.get());
    }

    static class Car{
        private String name;
        public Car(String name){
            this.name=name;
        }
    }
}
