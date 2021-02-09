package jdbclearning.jdbc3.dao;

import jdbclearning.jdbc3.DaoException;
import jdbclearning.jdbc3.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/27
 */
public abstract class AbstractDao {
    // 增删改
    public int update(String sql, Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            // sql由调用者传入
            ps = conn.prepareStatement(sql);
            // 遍历设置模板参数
            for (int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }

    //查询
    public Object query(String sql, Object[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            // sql由调用者传入
            ps = conn.prepareStatement(sql);
            // 遍历设置模板参数
            for (int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            Object obj = null;
            // 结果集映射，子类必须实现抽象方法rowMapper
            if (rs.next()) {
                //这里虽然调用的抽象方法，但是实现此抽象方法的内容在继承此类的实现类里实现了
                //子类通过super.query()调用
                obj = rowMapper(rs);
            }
            return obj;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }

    // 定义成抽象方法，让子类去实现
    abstract protected Object rowMapper(ResultSet rs);
}
