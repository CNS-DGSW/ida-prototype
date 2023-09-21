package kr.hs.dgsw.cns.aggregate.auth.domain;

import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthCode {
    private static final int EXPIRED = 180;
    public static final int SIZE = 6;

    private PhoneNumber phoneNumber;

    private String code;

    private Integer expirationTime;

    @Builder
    public AuthCode(String code, PhoneNumber phoneNumber, Integer expirationTime) {
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.expirationTime = expirationTime;
    }

    @Builder
    public AuthCode(String code, PhoneNumber phoneNumber) {
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.expirationTime = EXPIRED;
    }

}
