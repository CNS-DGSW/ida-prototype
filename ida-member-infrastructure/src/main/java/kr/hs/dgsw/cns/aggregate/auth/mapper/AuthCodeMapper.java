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
                .contact(domain.getContact().getNumber())
                .expirationTime(domain.getExpirationTime())
                .build();
    }

    @Override
    public AuthCode entityToDomain(AuthCodeEntity entity) {
        return AuthCode.builder()
                .code(entity.getCode())
                .contact(PhoneNumber.of(entity.getContact()))
                .expirationTime(entity.getExpirationTime())
                .build();
    }
}
