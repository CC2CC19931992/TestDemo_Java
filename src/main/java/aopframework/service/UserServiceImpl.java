package aopframework.service;

import aopframework.anotation.MyTransactional;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/25
 */
@MyTransactional
public class UserServiceImpl implements UserService {
    @Override
    public void getUser() {
        System.out.println("service执行...");
    }
}