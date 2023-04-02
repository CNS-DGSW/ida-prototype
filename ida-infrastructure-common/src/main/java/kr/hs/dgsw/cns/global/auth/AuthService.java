package kr.hs.dgsw.cns.global.auth;

/**
 * Auth 인가 대상, 즉 회원 객체를 식별값 {@link T}를 사용하여
 * 반환하도록 합니다.
 * @param <T> 식별값 타입
 */
public interface AuthService<T> {

    /**
     * 인가 대상(객체)를 식별값 {@link T}으로
     * 조회 후, 반환합니다.
     * @param identify 식별값
     * @return 인가 대상(객체)를 반환합니다.
     */
    Object loadAuthByIdentify(T identify);

}
