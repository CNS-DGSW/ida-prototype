package kr.hs.dgsw.cns.aggregate.auth.dao;

import kr.hs.dgsw.cns.aggregate.auth.domain.AuthCode;
import kr.hs.dgsw.cns.aggregate.auth.entity.AuthCodeEntity;
import kr.hs.dgsw.cns.aggregate.auth.mapper.AuthCodeMapper;
import kr.hs.dgsw.cns.aggregate.auth.spi.query.QueryAuthCodeSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthCodeQueryRepository implements QueryAuthCodeSpi {
    private final AuthCodeRepository authCodeRepository;
    private final AuthCodeMapper authCodeMapper;
    @Override
    public Optional<AuthCode> findById(String code) {
        AuthCodeEntity authCode = authCodeRepository.findByCode(code);
        if (authCode == null) {
            return Optional.empty();
        }
        return Optional.of(authCodeMapper.entityToDomain((authCode)));
    }
}
