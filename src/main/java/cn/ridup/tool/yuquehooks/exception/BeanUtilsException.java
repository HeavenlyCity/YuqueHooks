package cn.ridup.tool.yuquehooks.exception;

import org.springframework.http.HttpStatus;

/**
 * BeanUtils exception.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 */
public class BeanUtilsException extends AbstractYuqueException {

    public BeanUtilsException(String message) {
        super(message);
    }

    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
