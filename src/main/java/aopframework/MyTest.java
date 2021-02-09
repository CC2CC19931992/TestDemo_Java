package aopframework;

import aopframework.factory.BeanFactory;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/25
 */
public class MyTest {

    public static void main(String[] args){
        BeanFactory beanFactory = new BeanFactory();
        try {
            Object bean = beanFactory.getBean("aopframework.service.UserServiceImpl");
            System.out.println(bean.getClass().getName());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
