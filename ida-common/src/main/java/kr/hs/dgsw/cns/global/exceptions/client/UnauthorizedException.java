package kr.hs.dgsw.cns.global.exceptions.client;

import kr.hs.dgsw.cns.global.exceptions.BusinessException;

import java.io.Serial;

/**
 * HTTP 4xx 시리즈 에러들 가운데 401(Unauthorized)이
 * 비즈니스 로직 중 발생하게 되는 경우, 발생하는 예외 클래스 <br>
 * 보통 인증에 실패한 경우, 발생하게 됩니다.
 */
public class UnauthorizedException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -3887797664991378760L;

    public UnauthorizedException() {
        super(401, "권한이 없는 요청입니다.");
    }

    public UnauthorizedException(String message) {
        super(401, message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(401, message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(401, cause);
    }
}
