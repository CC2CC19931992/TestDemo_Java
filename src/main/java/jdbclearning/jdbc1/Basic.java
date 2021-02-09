package jdbclearning.jdbc1;

import java.sql.*;

/**
 * 直连方式
 *
 * @author tc
 * @date 2021/1/27
 */
public class Basic {
    //获取Connection的步骤太复杂，需要封装
    //资源释放太随意，不够规范
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        // 1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 2.建立连接
        String url = "jdbc:mysql://10.0.31.30:6606/rms_base";
        String user = "root";
        String password = "Ccinn@123456";
        Connection conn = DriverManager.getConnection(url, user, password);

        // 3.创建sql模板
        String sql = "select * from t_user where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // 4.设置模板参数
        preparedStatement.setInt(1, 3);

        // 4.执行语句
        ResultSet rs = preparedStatement.executeQuery();

        // 5.处理结果
        while (rs.next()) {
            System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
                    + rs.getObject(3) + "\t" + rs.getObject(4));
        }

        // 6.释放资源
        rs.close();
        preparedStatement.close();
        conn.close();
    }
}

