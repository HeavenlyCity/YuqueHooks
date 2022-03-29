package cn.ridup.tool.yuquehooks.exception;

/**
 * Theme not support exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 */
public class ThemeNotSupportException extends BadRequestException {

    public ThemeNotSupportException(String message) {
        super(message);
    }

    public ThemeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
