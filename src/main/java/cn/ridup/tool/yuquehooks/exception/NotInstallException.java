package cn.ridup.tool.yuquehooks.exception;

/**
 * Not install exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 19-4-29
 */
public class NotInstallException extends BadRequestException {

    public NotInstallException(String message) {
        super(message);
    }

    public NotInstallException(String message, Throwable cause) {
        super(message, cause);
    }
}
