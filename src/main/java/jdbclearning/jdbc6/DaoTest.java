package jdbclearning.jdbc6;

import jdbclearning.jdbc6.dao.UserDao;
import jdbclearning.jdbc6.dao.UserDaoJdbcImpl;
import jdbclearning.jdbc6.pojo.User;
import jdbclearning.jdbc6.utils.DaoFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/27
 */
public class DaoTest {
    public static void main(String[] args) throws SQLException {
//        UserDao userDao = new UserDaoJdbcImpl();
//        List<User> users=userDao.selectUsers(19);
//        for (User user:users) {
//            System.out.println(user) ;
//        }
        //通过工厂得到DAO实现类，如果想换成UserDaoImpl2，修改配置即可
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users = userDao.selectUsers(19);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
