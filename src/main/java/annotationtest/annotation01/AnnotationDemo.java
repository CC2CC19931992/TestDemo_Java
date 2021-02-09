package annotationtest.annotation01;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/20
 */
@MyAnnotation(getValue = "类名")
public class AnnotationDemo {

    @MyAnnotation(getValue = "字段")
    public String name;

    @MyAnnotation(getValue = "方法")
    public void hello(){

    }

    @MyAnnotation()
    public void defaultMethod(){

    }
}
