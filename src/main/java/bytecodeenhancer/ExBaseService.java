package bytecodeenhancer;

import java.util.Map;

/**
 * 步骤2：编写你需要的对业务类进行增强的类或者内容
 */
public class ExBaseService {
    public static  void exPrint(Map map){
        System.out.println("exbaseService增强的功能==需要增强的方法之前");
    }

    public static  void  exPrintL(Map map){
        System.out.println("exbaseService增强的功能==需要增强的方法之后");
    }
}
