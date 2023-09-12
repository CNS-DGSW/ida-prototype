package kr.hs.dgsw.cns.aggregate.member.mapper;

import kr.hs.dgsw.cns.aggregate.member.domain.Member;
import kr.hs.dgsw.cns.aggregate.member.domain.value.Role;
import kr.hs.dgsw.cns.aggregate.member.entity.MemberEntity;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper implements Mapper<Member, MemberEntity> {

    private final MemberIdMapper idMapper;

    @Override
    public MemberEntity domainToEntity(Member domain) {
        return MemberEntity.builder()
                .id(idMapper.domainToEntity(domain.getId()))
                .contact(domain.getContact().getNumber())
                .password(domain.getPassword())
                .role((domain.getRole() == null) ? Role.NONE : domain.getRole())
                .build();
    }

    @Override
    public Member entityToDomain(MemberEntity entity) {
        return Member.builder()
                .id(idMapper.entityToDomain(entity.getId()))
                .contact(PhoneNumber.of(entity.getContact()))
                .password(entity.getPassword())
                .role((entity.getRole() == null) ? Role.NONE : entity.getRole())
                .build();
    }
}
