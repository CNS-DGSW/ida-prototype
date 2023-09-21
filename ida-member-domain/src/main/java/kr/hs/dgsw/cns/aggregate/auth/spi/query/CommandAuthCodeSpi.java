package kr.hs.dgsw.cns.aggregate.auth.spi.query;

import kr.hs.dgsw.cns.aggregate.auth.domain.AuthCode;

public interface CommandAuthCodeSpi {
    void save(AuthCode authCode);
}
