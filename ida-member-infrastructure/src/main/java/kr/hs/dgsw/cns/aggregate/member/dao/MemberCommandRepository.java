package kr.hs.dgsw.cns.aggregate.member.dao;

import kr.hs.dgsw.cns.aggregate.member.entity.MemberEntity;
import kr.hs.dgsw.cns.global.embedd.MemberId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCommandRepository extends CrudRepository<MemberEntity, MemberId> {

}
