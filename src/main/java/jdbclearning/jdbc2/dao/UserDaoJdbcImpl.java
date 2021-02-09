package jdbclearning.jdbc2.dao;

import jdbclearning.jdbc2.DaoException;
import jdbclearning.jdbc2.JdbcUtils;
import jdbclearning.jdbc2.pojo.User;

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
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public int addUser(User user){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into t_user(name,age, birthday) values (?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setDate(3, new java.sql.Date(user.getBirthDay().getTime()));
            return ps.executeUpdate();
        } catch (SQLException ex){
            //DAO接口有声明异常SQLException，这等于向外界暴露DAO层是JDBC实现
            //而且针对该接口只能用关系型数据库
            // SQLException是关系型数据库的报错捕获，耦合度太高了。后期无法切换DAO实现
            //所以这里需要捕获编译时异常，转为运行时异常
            //转为DaoException（运行时异常）抛出，Service层可以不处理
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }

    @Override
    public int delete(User user){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "delete from t_user where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            System.out.println(sql);
            return ps.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }

    }

    @Override
    public int update(User user){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "update t_user set name=?, age=?, birthday=? where id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setDate(3, new java.sql.Date(user.getBirthDay().getTime()));
            ps.setInt(4, user.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }

    @Override
    public User findUser(String name, int age){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select id, name, birthday  from t_user where name=? and age=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, age);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setBirthDay(rs.getDate("birthday"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }finally {
            JdbcUtils.free(rs, ps, conn);
        }
        return user;
    }

    @Override
    public User getUser(int userId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select id, name, age, birthday from t_user where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setBirthDay(rs.getDate("birthday"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
        return user;
    }

}
