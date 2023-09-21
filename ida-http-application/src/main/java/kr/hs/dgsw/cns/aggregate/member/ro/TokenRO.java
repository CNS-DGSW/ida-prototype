package kr.hs.dgsw.cns.aggregate.member.ro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenRO {
    private final String accessToken;

    private final String refreshToken;

    private final long expireMillis;
}
