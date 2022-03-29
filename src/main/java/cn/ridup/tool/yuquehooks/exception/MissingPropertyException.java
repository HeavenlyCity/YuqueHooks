package cn.ridup.tool.yuquehooks.exception;

/**
 * Missing property value exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 3/22/19
 */
public class MissingPropertyException extends BadRequestException {

    public MissingPropertyException(String message) {
        super(message);
    }

    public MissingPropertyException(String message, Throwable cause) {
        super(message, cause);
    }
}
