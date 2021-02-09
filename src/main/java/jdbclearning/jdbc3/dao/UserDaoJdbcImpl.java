package jdbclearning.jdbc3.dao;

import jdbclearning.jdbc3.DaoException;
import jdbclearning.jdbc3.JdbcUtils;
import jdbclearning.jdbc3.pojo.User;

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
public class UserDaoJdbcImpl extends AbstractDao implements UserDao {

    //增
    @Override
    public int addUser(User user) {
        String sql = "insert into t_user(name, age, birthday) values (?,?,?) ";
        Object[] args = new Object[]{user.getName(), user.getAge(),
                user.getBirthDay()};
        //调用父类AbstractDao的方法
        return super.update(sql, args);
    }

    //删
    @Override
    public int update(User user) {
        String sql = "update t_user set name=?, age=?, birthday=? where id=? ";
        Object[] args = new Object[]{user.getName(), user.getAge(),
                user.getBirthDay(), user.getId()};
        return super.update(sql, args);
    }

    //改
    @Override
    public int delete(User user) {
        String sql = "delete from t_user where id=?";
        Object[] args = new Object[]{user.getId()};
        return super.update(sql, args);
    }
    //查询
    @Override
    public User getUser(int id) {
        String sql = "select id, name, age, birthday from t_user where id=?";
        Object[] args = new Object[]{id};
        Object user = super.query(sql, args);
        return (User) user;
    }

    //查询
    @Override
    public User findUser(String name, int age) {
        String sql = "select id, name, age, birthday from t_user where name=? and age=?";
        Object[] args = new Object[]{name, age};
        Object user = super.query(sql, args);
        return (User) user;
    }

    //UserDaoImpl的结果集映射器
    @Override
    protected Object rowMapper(ResultSet rs) {
        User user = null;
        try {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setAge(rs.getInt("age"));
            user.setName(rs.getString("name"));
            user.setBirthDay(rs.getDate("birthday"));
        } catch (SQLException e) {
            throw new DaoException("mapping error");
        }
        return user;
    }

}
