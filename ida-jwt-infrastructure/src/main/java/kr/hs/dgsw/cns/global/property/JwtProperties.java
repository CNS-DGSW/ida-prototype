package kr.hs.dgsw.cns.global.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtProperties {

    private final String secret;
    private final String refreshSecret;
    private final long expirationMillis;

}
