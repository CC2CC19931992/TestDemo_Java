package jdbclearning.jdbc4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/27
 */
public interface RowMapper {
    //映射结果集
    Object mapRow(ResultSet rs) throws SQLException;
}
