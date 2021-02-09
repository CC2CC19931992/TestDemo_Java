package annotationtest.annotation01;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/20
 */
public class RunAnnotation {

    public static void main(String[] args) throws Exception{
        Class<AnnotationDemo> clazz = AnnotationDemo.class;
        //获取类上的注解
        MyAnnotation annotationClass = clazz.getAnnotation(MyAnnotation.class);
        System.out.println(annotationClass.getValue());

        //获取成员变量上的注解
        Field nameField = clazz.getField("name");
        MyAnnotation annotationField = nameField.getAnnotation(MyAnnotation.class);
        System.out.println(annotationField.getValue());

        //获取hello方法上的注解
        Method helloMethod = clazz.getMethod("hello",null);
        MyAnnotation annotationMethod = helloMethod.getAnnotation(MyAnnotation.class);
        System.out.println(annotationMethod.getValue());

        //获取defaultMethod方法上的注解
        Method defaultMethod = clazz.getMethod("defaultMethod",null);
        MyAnnotation annotationDefaultMethod = defaultMethod.getAnnotation(MyAnnotation.class);
        System.out.println(annotationDefaultMethod.getValue());

    }

}
