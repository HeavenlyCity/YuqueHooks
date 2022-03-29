package cn.ridup.tool.yuquehooks.exception;

/**
 * Theme property missing exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 4/11/19
 */
public class ThemePropertyMissingException extends BadRequestException {

    public ThemePropertyMissingException(String message) {
        super(message);
    }

    public ThemePropertyMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
