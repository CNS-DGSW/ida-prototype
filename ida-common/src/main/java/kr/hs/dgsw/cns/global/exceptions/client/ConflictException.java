package kr.hs.dgsw.cns.global.exceptions.client;

import kr.hs.dgsw.cns.global.exceptions.BusinessException;

import java.io.Serial;

/**
 * HTTP 4xx 에러 시리즈 중 409(Conflict)가
 * 비즈니스 로직 중 발생하게 되는 경우, 발생하는 예외 클래스 <br>
 * 보통 리소스간 충돌 혹은 기존 작업과의 충돌이 발생할 때 발생하게 됩니다.
 */
public class ConflictException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 7199746423396108406L;

    public ConflictException() {
        super(409, "리소스 간 충돌이 발생하였습니다.");
    }

    public ConflictException(String message) {
        super(409, message);
    }

    public ConflictException(String message, Throwable cause) {
        super(409, message, cause);
    }

    public ConflictException(Throwable cause) {
        super(409, cause);
    }
}
