package cn.ridup.tool.yuquehooks.exception;

/**
 * Theme update exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 19-5-30
 */
public class ThemeUpdateException extends ServiceException {

    public ThemeUpdateException(String message) {
        super(message);
    }

    public ThemeUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
