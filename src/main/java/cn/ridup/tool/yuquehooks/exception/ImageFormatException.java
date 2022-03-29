package cn.ridup.tool.yuquehooks.exception;

/**
 * Image format exception.
 *
 * @author ZhiXiang Yuan
 * @date 2020/08/10 02:11
 */
public class ImageFormatException extends BadRequestException {

    public ImageFormatException(String message) {
        super(message);
    }

    public ImageFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
