package cn.ridup.tool.yuquehooks.exception;

/**
 * File operation exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 3/27/19
 */
public class FileOperationException extends ServiceException {
    public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
