package cn.ridup.tool.yuquehooks.exception;

/**
 * Theme configuration missing exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 4/11/19
 */
public class ThemeConfigMissingException extends BadRequestException {

    public ThemeConfigMissingException(String message) {
        super(message);
    }

    public ThemeConfigMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
