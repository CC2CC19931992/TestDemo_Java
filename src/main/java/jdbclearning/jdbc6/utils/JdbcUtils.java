package jdbclearning.jdbc6.utils;

import java.sql.*;

/**
 * JdbcUtils-2.0
 *
 * @author tc
 * @date 2021/1/27
 */
public class JdbcUtils {

    //初始化一个数据源
    private static MyDatasource datasource = new MyDatasource();

    // 获取连接
    public static Connection getConnection() throws SQLException {
        //从数据源获取Connection并返回
        return datasource.getConnection();
    }

    // 获取数据源
    public static MyDatasource getDatasource(){
        return datasource;
    }

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
