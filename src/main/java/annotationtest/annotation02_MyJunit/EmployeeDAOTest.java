package annotationtest.annotation02_MyJunit;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/20
 */
public class EmployeeDAOTest {
    @MyBefore
    public void init() {
        System.out.println("初始化...");
    }

    @MyAfter
    public void destroy() {
        System.out.println("销毁...");
    }

    @MyTest
    public void testSave() {
        System.out.println("save...");
    }

    @MyTest
    public void testDelete() {
        System.out.println("delete...");
    }
}
