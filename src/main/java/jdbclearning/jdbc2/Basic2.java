package jdbclearning.jdbc2;

import java.sql.*;

/**
 * 在Basic上的改进版 - 使用了工具类
 *
 * @author tc
 * @date 2021/1/27
 */
public class Basic2 {
    public static void main(String[] args) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.获取连接
            conn = JdbcUtils.getConnection();

            // 2.创建sql模板
            String sql = "select * from t_user where id = ?";
            ps = conn.prepareStatement(sql);

            // 3.设置模板参数
            ps.setInt(1, 3);

            // 4.执行语句
            rs = ps.executeQuery();

            // 5.处理结果
            while (rs.next()) {
                System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
                        + rs.getObject(3) + "\t" + rs.getObject(4));
            }
        } finally {
            // 6.释放资源
            JdbcUtils.free(rs, ps, conn);
        }
    }
}
