package jdbclearning.jdbc4.dao;

import jdbclearning.jdbc4.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    int addUser(User user) throws SQLException;
    int update(User user) throws SQLException;
    int delete(User user) throws SQLException;
    User getUser(int Id) throws SQLException;
    User findUser(String name, int age) throws SQLException;
    List<User> selectUsers(int age) throws SQLException;
}
