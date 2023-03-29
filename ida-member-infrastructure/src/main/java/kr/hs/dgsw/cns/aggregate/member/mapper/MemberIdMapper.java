package kr.hs.dgsw.cns.aggregate.member.mapper;

import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import kr.hs.dgsw.cns.global.util.IdGenerator;
import org.springframework.stereotype.Component;

@Component
public class MemberIdMapper implements Mapper<MemberId, kr.hs.dgsw.cns.global.embedd.MemberId> {

    @Override
    public kr.hs.dgsw.cns.global.embedd.MemberId domainToEntity(MemberId domain) {
        if (IdGenerator.isNull(domain)) {
            return kr.hs.dgsw.cns.global.embedd.MemberId.of(IdGenerator.generateUUIDWithLong());
        }

        return kr.hs.dgsw.cns.global.embedd.MemberId.of(domain.getId());
    }

    @Override
    public MemberId entityToDomain(kr.hs.dgsw.cns.global.embedd.MemberId entity) {
        if (IdGenerator.isNull(entity)) {
            return new MemberId(IdGenerator.generateUUIDWithLong());
        }

        return new MemberId(entity.getId());
    }
}
