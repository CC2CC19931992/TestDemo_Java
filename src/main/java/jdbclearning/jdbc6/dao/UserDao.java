package jdbclearning.jdbc6.dao;

import jdbclearning.jdbc6.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    int addUser(User user) throws SQLException;
    int update(User user) throws SQLException;
    int delete(User user) throws SQLException;
    User getUser(Long Id) throws SQLException;
    User findUser(String name, Integer age) throws SQLException;
    List<User> selectUsers(Integer age) throws SQLException;
}
