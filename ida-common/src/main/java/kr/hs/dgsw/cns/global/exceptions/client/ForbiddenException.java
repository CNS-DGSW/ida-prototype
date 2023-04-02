package kr.hs.dgsw.cns.global.exceptions.client;

import kr.hs.dgsw.cns.global.exceptions.BusinessException;

import java.io.Serial;

/**
 * HTTP 4xx 에러 시리즈 중 403(Forbidden)이
 * 비즈니스 로직 중 발생하게 되는 경우, 발생하는 예외 클래스 <br>
 * 보통 인가(권한 부족)일 때 발생하게 됩니다.
 */
public class ForbiddenException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -9021620174507117969L;

    public ForbiddenException() {
        super(403, "요청의 접근이 금지되었습니다.");
    }

    public ForbiddenException(String message) {
        super(403, message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(403, message, cause);
    }

    public ForbiddenException(Throwable cause) {
        super(403, cause);
    }
}
