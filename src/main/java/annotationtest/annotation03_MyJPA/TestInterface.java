package annotationtest.annotation03_MyJPA;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/21
 */
public class TestInterface {
    public static void main(String [] args){
        IDemo1 demo1 = new Demo1Impl();
        //IDemo2 demo2 = (IDemo2)demo1;
        System.out.println(demo1);
        System.out.println(demo1.getClass());
        System.out.println(demo1.getName());
    }
}

interface IDemo1{
    String getName();
}

interface IDemo2 extends IDemo1{

}

class Demo1Impl implements IDemo2{
    String name;

    public Demo1Impl(){
        name="默认";
    }

    public Demo1Impl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

class Demo2Impl implements IDemo1{

    @Override
    public String getName() {
        return null;
    }
}


