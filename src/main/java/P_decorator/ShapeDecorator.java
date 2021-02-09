package P_decorator;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/3
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    //因为此类是抽象类，这个类的对象不能被new出来，
    //但是可以在继承该类的子类种调用这个构造方法
    //下面这个无参构造也是如此
    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public ShapeDecorator(){
    }

    @Override
    public void draw(){
        decoratedShape.draw();
    }
}
