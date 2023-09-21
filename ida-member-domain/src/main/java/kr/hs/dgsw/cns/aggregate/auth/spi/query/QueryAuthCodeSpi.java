package kr.hs.dgsw.cns.aggregate.auth.spi.query;

import kr.hs.dgsw.cns.aggregate.auth.domain.AuthCode;
import kr.hs.dgsw.cns.domain.spi.QuerySpi;

import java.util.Optional;

public interface QueryAuthCodeSpi extends QuerySpi<AuthCode, String> {
}
