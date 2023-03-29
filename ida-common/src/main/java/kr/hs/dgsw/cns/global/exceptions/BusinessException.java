package kr.hs.dgsw.cns.global.exceptions;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 979906615598312994L;

    private final int code;
    private final String message;

    protected BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    protected BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    protected BusinessException(int code, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = cause.getMessage();
    }
}
