package jdbclearning.jdbc6.exception;

/**
 * 自定义DaoException
 *
 * @author tc
 * @date 2021/1/27
 */
public class DaoException extends RuntimeException {
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
