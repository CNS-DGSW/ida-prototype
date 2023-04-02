package kr.hs.dgsw.cns.global.auth;

public interface AuthService<T> {

    Object loadAuthByIdentify(T identify);

}
