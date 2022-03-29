package cn.ridup.tool.yuquehooks.exception;

/**
 * Exception caused by entity existence already.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 */
public class AlreadyExistsException extends BadRequestException {

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
