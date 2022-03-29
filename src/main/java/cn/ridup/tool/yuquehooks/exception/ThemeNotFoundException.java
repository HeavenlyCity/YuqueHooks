package cn.ridup.tool.yuquehooks.exception;

/**
 * Theme not found exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 2020-03-01
 */
public class ThemeNotFoundException extends BadRequestException {

    public ThemeNotFoundException(String message) {
        super(message);
    }

    public ThemeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
