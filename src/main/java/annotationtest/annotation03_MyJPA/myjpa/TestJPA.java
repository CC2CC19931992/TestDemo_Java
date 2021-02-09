package annotationtest.annotation03_MyJPA.myjpa;

import java.sql.Driver;
import java.sql.SQLException;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/21
 */
public class TestJPA {
    public static void main(String[] args) throws SQLException {
        Driver driver =  new com.mysql.jdbc.Driver();
        //未用注解的JPADemo
        UserDao userDao = new UserDao();
        User user = new User("hst", 20);
        userDao.add(user);

        //用了注解的JPADemo
        UserDaoNew userDaoNew = new UserDaoNew();
        User userNew = new User("hst", 20);
        userDaoNew.add(userNew);
    }
}
