package cn.ridup.tool.yuquehooks.exception;

/**
 * Frequent access exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 * @date 3/28/19
 */
public class FrequentAccessException extends BadRequestException {

    public FrequentAccessException(String message) {
        super(message);
    }

    public FrequentAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
