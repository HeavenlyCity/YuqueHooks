package cn.ridup.tool.yuquehooks.exception;

/**
 * repeat type exception
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 */
public class RepeatTypeException extends ServiceException {
    public RepeatTypeException(String message) {
        super(message);
    }

    public RepeatTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
