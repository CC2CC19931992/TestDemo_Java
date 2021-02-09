package bytecodeenhancer;

import java.util.Map;

/**
 * 步骤1：编写你最初的业务类，实现业务功能
 */
public class BaseService {
    public void basePrint(Map map){
        System.out.println(map.toString());
    }
}