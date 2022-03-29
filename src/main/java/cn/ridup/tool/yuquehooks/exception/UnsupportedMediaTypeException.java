package cn.ridup.tool.yuquehooks.exception;

/**
 * Unsupported media type exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 19-4-19
 */
public class UnsupportedMediaTypeException extends BadRequestException {

    public UnsupportedMediaTypeException(String message) {
        super(message);
    }

    public UnsupportedMediaTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
