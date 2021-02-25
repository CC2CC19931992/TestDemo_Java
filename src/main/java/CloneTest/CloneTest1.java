package CloneTest;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/2/21
 */
public class CloneTest1 {

    public static void main(String [] args){
        Example e1 = new Example();
        System.out.println(e1.hashCode());
        try {
            Example e2 = (Example)e1.clone();
            System.out.println(e2.toString());
            System.out.println(e2.hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

class Example implements Cloneable{
    private int a;
    private int b;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
