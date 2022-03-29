package cn.ridup.tool.yuquehooks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Base exception of the project.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 */
public abstract class AbstractYuqueException extends RuntimeException {

    /**
     * Error errorData.
     */
    private Object errorData;

    public AbstractYuqueException(String message) {
        super(message);
    }

    public AbstractYuqueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Http status code
     *
     * @return {@link HttpStatus}
     */
    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public AbstractYuqueException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
