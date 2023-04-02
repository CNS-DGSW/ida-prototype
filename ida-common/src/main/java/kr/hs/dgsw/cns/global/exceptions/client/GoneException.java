package kr.hs.dgsw.cns.global.exceptions.client;

import kr.hs.dgsw.cns.global.exceptions.BusinessException;

import java.io.Serial;

/**
 * HTTP 4xx 에러 시리즈 중 410(Gone)이
 * 비즈니스 로직 중 발생하게 되는 경우, 발생하는 예외 클래스 <br>
 * 보통 해당 리소스가 존재했다가 임의로 삭제되었을 때 발생하게 됩니다.
 */
public class GoneException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 5033082193438936786L;

    public GoneException() {
        super(410, "서버에서 임의로 삭제되어 더 이상 자원을 찾을 수 없습니다.");
    }

    public GoneException(String message) {
        super(410, message);
    }

    public GoneException(String message, Throwable cause) {
        super(410, message, cause);
    }

    public GoneException(Throwable cause) {
        super(410, cause);
    }
}
