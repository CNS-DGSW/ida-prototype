package kr.hs.dgsw.cns.global.exceptions.client;

import kr.hs.dgsw.cns.global.exceptions.BusinessException;

import java.io.Serial;

/**
 * HTTP 4xx 에러 시리즈 중 404(Not_Found)가
 * 비즈니스 로직 중 발생하게 되는 경우, 발생하게 되는 예외 클래스 <br>
 * 보통 해당 리소스가 존재하지 않을 때 발생하게 됩니다.
 */
public class NotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 8203154193900797635L;

    public NotFoundException() {
        super(404, "존재하지 않은 URI 혹은 리소스입니다.");
    }

    public NotFoundException(String message) {
        super(404, message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(404, message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(404, cause);
    }
}
