package jdbclearning.jdbc4.dao;

import jdbclearning.jdbc4.DaoException;
import jdbclearning.jdbc4.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/27
 */
public class UserDaoJdbcImpl  implements UserDao {
    MyJDBCTemplate jdbcTemplate = new MyJDBCTemplate();
    //增
    @Override
    public int addUser(User user) {
        String sql = "insert into t_user(name, age, birthday) values (?,?,?) ";
        Object[] args = new Object[]{user.getName(), user.getAge(),
                user.getBirthDay()};
        //调用jdbcTemplate的update方法
        return jdbcTemplate.update(sql, args);
    }

    //删
    @Override
    public int update(User user) {
        String sql = "update t_user set name=?, age=?, birthday=? where id=? ";
        Object[] args = new Object[]{user.getName(), user.getAge(),
                user.getBirthDay(), user.getId()};
        //调用jdbcTemplate的update方法
        return jdbcTemplate.update(sql, args);
    }

    //改
    @Override
    public int delete(User user) {
        String sql = "delete from t_user where id=?";
        Object[] args = new Object[]{user.getId()};
        //调用jdbcTemplate的update方法
        return jdbcTemplate.update(sql, args);
    }

    //查询
    @Override
    public User getUser(int id) {
        String sql = "select id, name, age, birthday from t_user where id=?";
        Object[] args = new Object[]{id};
        //调用jdbcTemplate的query方法，传入sql，args，RowMapper匿名对象
        List list = jdbcTemplate.query(sql, args, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setBirthDay(rs.getDate("birthday"));
                return user;
            }
        });
        return (User)list.get(0);
    }

    //查询
    @Override
    public User findUser(String name, int age) {
        String sql = "select id, name, age, birthday from t_user where name=? and age=?";
        Object[] args = new Object[]{name, age};
        //调用jdbcTemplate的query方法，传入sql，args，RowMapper匿名对象
        List list = jdbcTemplate.query(sql, args, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setBirthDay(rs.getDate("birthday"));
                return user;
            }
        });
        return (User)list.get(0);
    }

    //查询多个
    @Override
    public List selectUsers(int age) {
        String sql = "select id, name, age, birthday from t_user where age=?";
        Object[] args = new Object[]{age};
        //调用jdbcTemplate的query方法，传入sql，args，RowMapper匿名对象
        List list = jdbcTemplate.query(sql, args, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setBirthDay(rs.getDate("birthday"));
                return user;
            }
        });
        return list;
    }

}
