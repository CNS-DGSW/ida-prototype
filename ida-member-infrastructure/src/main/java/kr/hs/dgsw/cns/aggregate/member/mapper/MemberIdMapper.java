package kr.hs.dgsw.cns.aggregate.member.mapper;

import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.global.embedd.EmbeddedMemberId;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import kr.hs.dgsw.cns.global.util.IdGenerator;
import kr.hs.dgsw.cns.global.util.MapperUtils;
import org.springframework.stereotype.Component;

@Component
public class MemberIdMapper implements Mapper<MemberId, EmbeddedMemberId> {

    @Override
    public EmbeddedMemberId domainToEntity(MemberId domain) {
        if (MapperUtils.isNull(domain)) {
            return EmbeddedMemberId.of(IdGenerator.generateUUIDWithLong());
        }

        return EmbeddedMemberId.of(domain.getId());
    }

    @Override
    public MemberId entityToDomain(EmbeddedMemberId entity) {
        if (MapperUtils.isNull(entity)) {
            return new MemberId(IdGenerator.generateUUIDWithLong());
        }

        return new MemberId(entity.getId());
    }
}
