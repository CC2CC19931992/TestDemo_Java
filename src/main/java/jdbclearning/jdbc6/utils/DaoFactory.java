package jdbclearning.jdbc6.utils;

import jdbclearning.jdbc6.dao.UserDao;

import java.io.InputStream;
import java.util.Properties;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/30
 */
public class DaoFactory {
    private static UserDao userDao = null;
    private static DaoFactory daoFactory = new DaoFactory();

    private DaoFactory() {
        try {
            Properties prop = new Properties();
            InputStream inStream = DaoFactory.class.getClassLoader()
                    .getResourceAsStream("daoconfig.properties");
            prop.load(inStream);
            //从配置文件中读取UserDao的实现类全类名
            String userDaoClass = prop.getProperty("userDao");
            Class userDaoImplClazz = Class.forName(userDaoClass);
            //反射创建对象
            userDao = (UserDao) userDaoImplClazz.newInstance();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DaoFactory getInstance() {
        return daoFactory;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
