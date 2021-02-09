package jdbclearning.jdbc4;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JdbcUtils-1.0
 *
 * @author tc
 * @date 2021/1/27
 */
public class JdbcUtils {
    private static Properties props = null;

    // 只在JdbcUtils类被加载时执行一次！
    static {
        // 给props进行初始化，即加载jdbc.properties文件到props对象中
        try {
            InputStream in = JdbcUtils.class.getClassLoader()
                    .getResourceAsStream("jdbc.properties");
            props = new Properties();
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 加载驱动类 todo:[注释掉这段也不会报错 原因是？JDBC4.0之后 使用SPI机制自动发现驱动]
//        try {
//            Class.forName(props.getProperty("driver"));
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        // 得到Connection
        return DriverManager.getConnection(props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password"));
    }

    // 释放连接
    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null){
                rs.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null){
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
