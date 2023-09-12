package kr.hs.dgsw.cns.aggregate.member.service;

import kr.hs.dgsw.cns.aggregate.applicant.dao.ApplicantRepository;
import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.member.dao.MemberCommandRepository;
import kr.hs.dgsw.cns.aggregate.member.domain.Member;
import kr.hs.dgsw.cns.aggregate.member.domain.value.Password;
import kr.hs.dgsw.cns.aggregate.member.entity.MemberEntity;
import kr.hs.dgsw.cns.aggregate.member.mapper.MemberIdMapper;
import kr.hs.dgsw.cns.aggregate.member.mapper.MemberMapper;
import kr.hs.dgsw.cns.aggregate.member.spi.service.MemberRegisterService;
import kr.hs.dgsw.cns.aggregate.member.dto.MemberRequest;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberRegisterServiceImpl implements MemberRegisterService {

    private final MemberMapper memberMapper;
    private final MemberIdMapper idMapper;
    private final MemberCommandRepository memberCommandRepository;
    private final ApplicantRepository applicantRepository;

    @Override
    public void register(MemberRequest registerRequest) {
        MemberEntity member = memberMapper.domainToEntity(
                Member.builder()
                        .contact(PhoneNumber.of(registerRequest.getContact()))
                        .password(new Password(registerRequest.getPassword()))
                        .build()
        );
        Applicant applicant = Applicant.builder()
                .id(idMapper.entityToDomain(member.getId()))
                .build();

        memberCommandRepository.save(member);
        applicantRepository.save(applicant);
    }
}
