package JavaGuideTest;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/4/14
 */
public class StaticTest {
}

class Person{

    public static void printSome(){
        Person person = new Person();
        System.out.println("111111111");
    }

    private String name;

    private String sex;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
