package aopframework.factory;

import aopframework.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 *
 * @author tc
 * @date 2021/1/25
 */
public class ProxyFactoryBean {
    //通知
    private TransactionManager txManager;
    //目标对象
    private Object target;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    //传入目标对象target，为它装配好通知，返回代理对象
    public Object getProxy() {
        Object proxy = Proxy.newProxyInstance(
                /*1.类加载器*/
                target.getClass().getClassLoader(),
                /*2.目标对象实现的接口*/
                target.getClass().getInterfaces(),
                /*3.InvocationHandler*/
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            //1.开启事务
                            txManager.beginTransaction();
                            //2.执行操作
                            Object retVal = method.invoke(target, args);
                            //3.提交事务
                            txManager.commit();
                            //4.返回结果
                            return retVal;
                        } catch (Exception e) {
                            //5.回滚事务
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            //6.释放连接
                            txManager.release();
                        }

                    }
                }
        );
        return proxy;
    }

}
