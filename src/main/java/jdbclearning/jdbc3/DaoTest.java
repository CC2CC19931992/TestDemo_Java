package jdbclearning.jdbc3;

import jdbclearning.jdbc3.dao.UserDao;
import jdbclearning.jdbc3.dao.UserDaoJdbcImpl;
import jdbclearning.jdbc3.pojo.User;

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
        System.out.println(userDao.getUser(1).toString()) ;
    }
}
