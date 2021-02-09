package bytecodeenhancer;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * 字节码增强器
 */
public class IntecepterTest {
    /**
     * 调用增强工具类，使最初的业务类进行增强，
     * 随后调用业务类，查看是否增强
     * @param args
     */
    public static void main(String[] args)  {
        try {
            ServiceUtils.done();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
        BaseService baseService = new BaseService();
        Map<String,String> map1 = new HashMap<>();
        map1.put("name","quan");
        baseService.basePrint(map1);
    }
}
