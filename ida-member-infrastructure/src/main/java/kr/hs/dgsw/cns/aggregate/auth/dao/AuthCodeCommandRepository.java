package kr.hs.dgsw.cns.aggregate.auth.dao;

import kr.hs.dgsw.cns.aggregate.auth.domain.AuthCode;
import kr.hs.dgsw.cns.aggregate.auth.entity.AuthCodeEntity;
import kr.hs.dgsw.cns.aggregate.auth.mapper.AuthCodeMapper;
import kr.hs.dgsw.cns.aggregate.auth.spi.query.CommandAuthCodeSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthCodeCommandRepository implements CommandAuthCodeSpi {
    private final AuthCodeRepository authCodeRepository;
    private final AuthCodeMapper authCodeMapper;

    @Override
    public void save(AuthCode code) {
        authCodeRepository.save(authCodeMapper.domainToEntity(code));
    }
}
