package cn.ridup.tool.yuquehooks.exception;

/**
 * Property format exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 3/27/19
 */
public class PropertyFormatException extends BadRequestException {

    public PropertyFormatException(String message) {
        super(message);
    }

    public PropertyFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
