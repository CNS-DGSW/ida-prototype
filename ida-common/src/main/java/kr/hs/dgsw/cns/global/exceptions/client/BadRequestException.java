package kr.hs.dgsw.cns.global.exceptions.client;

import kr.hs.dgsw.cns.global.exceptions.BusinessException;

import java.io.Serial;

/**
 * HTTP 4xx 에러 시리즈 중 가장 광범위한 예외를 담당하는 예외 클래스
 * 정확하게 어떤 것이 문제인지 HTTP 4xx 에러 시리즈로 표현하기
 * 어려울 경우, 해당 예외 클래스를 사용하길 권장합니다.
 */
public class BadRequestException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -6757242672830219821L;

    public BadRequestException() {
        super(400, "잘못된 요청입니다.");
    }

    public BadRequestException(String message) {
        super(400, message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(400, message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(400, cause);
    }
}
