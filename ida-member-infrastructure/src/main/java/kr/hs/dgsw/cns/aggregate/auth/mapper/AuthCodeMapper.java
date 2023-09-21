package kr.hs.dgsw.cns.aggregate.auth.mapper;

import kr.hs.dgsw.cns.aggregate.auth.domain.AuthCode;
import kr.hs.dgsw.cns.aggregate.auth.entity.AuthCodeEntity;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthCodeMapper implements Mapper<AuthCode, AuthCodeEntity> {
    @Override
    public AuthCodeEntity domainToEntity(AuthCode domain) {
        return AuthCodeEntity.builder()
                .code(domain.getCode())
                .phoneNumber(domain.getPhoneNumber().getNumber())
                .expirationTime(domain.getExpirationTime())
                .build();
    }

    @Override
    public AuthCode entityToDomain(AuthCodeEntity entity) {
        return AuthCode.builder()
                .code(entity.getCode())
                .phoneNumber(PhoneNumber.of(entity.getPhoneNumber()))
                .expirationTime(entity.getExpirationTime())
                .build();
    }
}
