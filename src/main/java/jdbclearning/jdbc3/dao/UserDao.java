package jdbclearning.jdbc3.dao;

import jdbclearning.jdbc3.pojo.User;

import java.sql.SQLException;

public interface UserDao {
    int addUser(User user) throws SQLException;
    int update(User user) throws SQLException;
    int delete(User user) throws SQLException;
    User getUser(int Id) throws SQLException;
    User findUser(String name, int age) throws SQLException;
}
