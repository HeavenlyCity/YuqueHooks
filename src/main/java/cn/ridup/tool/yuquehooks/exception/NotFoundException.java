package cn.ridup.tool.yuquehooks.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception of entity not found.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 */
public class NotFoundException extends AbstractYuqueException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
