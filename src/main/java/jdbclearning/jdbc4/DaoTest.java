package jdbclearning.jdbc4;

import jdbclearning.jdbc4.dao.UserDao;
import jdbclearning.jdbc4.dao.UserDaoJdbcImpl;

import java.sql.SQLException;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/27
 */
public class DaoTest {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoJdbcImpl();
        System.out.println(userDao.selectUsers(19).toString()) ;
    }
}
