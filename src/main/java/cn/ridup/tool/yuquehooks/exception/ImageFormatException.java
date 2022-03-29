package cn.ridup.tool.yuquehooks.exception;

/**
 * Image format exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 */
public class ImageFormatException extends BadRequestException {

    public ImageFormatException(String message) {
        super(message);
    }

    public ImageFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
