package kr.hs.dgsw.cns.aggregate.auth.domain;

import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AuthCode {
    private String code;
    private PhoneNumber contact;
    private Integer expirationTime;
}
