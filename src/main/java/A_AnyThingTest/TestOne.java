package A_AnyThingTest;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/4
 */
public class TestOne {
    public static void main(String []args){
        Animal animal = new Animal(){
            String name;

            @Override
            protected void getName(){
                System.out.println("人类");
            }

            @Override
            public void getAge(){
                System.out.println("ca");
            }
        };
        System.out.println(animal.getClass());
        Animal animal1= new Animal();
        System.out.println(animal1.getClass());
        System.out.println("结束");

        Biology biology = new Biology();
        Animal animal11=(Animal) biology;
    }
}

class Animal extends Biology{
    protected void getName(){
        System.out.println("默认");
    }

    public void getAge(){
        System.out.println("默认年纪");
    }
}

class Biology{
}