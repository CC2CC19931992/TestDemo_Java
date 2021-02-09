package annotationtest.annotation01;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/20
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String getValue() default "no description";
}
