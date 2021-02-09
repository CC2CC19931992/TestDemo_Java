package jdbclearning.jdbc2;

import jdbclearning.jdbc2.dao.UserDao;
import jdbclearning.jdbc2.dao.UserDaoJdbcImpl;
import jdbclearning.jdbc2.pojo.User;

import java.sql.SQLException;
import java.util.Date;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/27
 */
public class DaoTest {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoJdbcImpl();
        User user = new User();
        user.setAge(19);
        user.setName("little ming");
        user.setBirthDay(new Date());
        userDao.addUser(user);
    }
}
