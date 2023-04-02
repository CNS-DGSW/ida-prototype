package kr.hs.dgsw.cns.global.exceptions;

import lombok.Getter;

import java.io.Serial;

/**
 * 비즈니스 로직에서 예외가 발생하게 될 경우, 발생하게 되는 예외 클래스
 * domain 레이어, infrastructure 레이어 뿐 아니라 facade 레이어
 * 등에서도 발생할 수 있는 예외 클래스 <br>
 * 단, 해당 예외 클래스는 사용자가 접근할 수 있는 범위 내에서만 사용하길
 * 권장합니다.
 */
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
