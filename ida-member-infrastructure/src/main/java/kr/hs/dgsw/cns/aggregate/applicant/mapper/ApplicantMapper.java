package kr.hs.dgsw.cns.aggregate.applicant.mapper;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Address;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.ParentInfo;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Privacy;
import kr.hs.dgsw.cns.aggregate.applicant.entity.ApplicantEntity;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.AddressVO;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.ParentVO;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.PersonalInformation;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.domain.SchoolCode;
import kr.hs.dgsw.cns.global.embedd.EmbeddedMemberId;
import kr.hs.dgsw.cns.global.embedd.SchoolCodeVO;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import kr.hs.dgsw.cns.global.util.MapperUtils;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper implements Mapper<Applicant, ApplicantEntity> {

    private static final InformationMapper INFO_MAPPER = new InformationMapper();
    private static final AddressMapper ADDRESS_MAPPER = new AddressMapper();
    private static final ParentMapper PARENT_MAPPER = new ParentMapper();

    @Override
    public ApplicantEntity domainToEntity(Applicant domain) {
        return ApplicantEntity.builder()
                .embeddedMemberId(EmbeddedMemberId.of(domain.getId().getId()))
                .information(MapperUtils.convertToEntityIsNull(domain.getPrivacy(), INFO_MAPPER))
                .build();
    }

    @Override
    public Applicant entityToDomain(ApplicantEntity entity) {
        return Applicant.builder()
                .id(new MemberId(entity.getEmbeddedMemberId().getId()))
                .privacy(MapperUtils.convertToDomainIsNull(entity.getInformation(), INFO_MAPPER))
                .build();
    }

    static class InformationMapper implements Mapper<Privacy, PersonalInformation> {

        @Override
        public PersonalInformation domainToEntity(Privacy domain) {
            return PersonalInformation.builder()
                    .name(domain.getName())
                    .birth(domain.getBirth())
                    .gender(domain.getGender())
                    .phone(domain.getPhone())
                    .school(MapperUtils.isNull(domain.getSchool()) ? null : SchoolCodeVO.of(domain.getSchool().getCode()))
                    .address(MapperUtils.convertToEntityIsNull(domain.getAddress(), ADDRESS_MAPPER))
                    .parent(MapperUtils.convertToEntityIsNull(domain.getParentInfo(), PARENT_MAPPER))
                    .build();
        }

        @Override
        public Privacy entityToDomain(PersonalInformation entity) {
            return Privacy.builder()
                    .name(entity.getName())
                    .birth(entity.getBirth())
                    .gender(entity.getGender())
                    .phone(entity.getPhone())
                    .school(MapperUtils.isNull(entity.getSchool()) ? null : new SchoolCode(entity.getSchool().getCode()))
                    .address(MapperUtils.convertToDomainIsNull(entity.getAddress(), ADDRESS_MAPPER))
                    .parentInfo(MapperUtils.convertToDomainIsNull(entity.getParent(), PARENT_MAPPER))
                    .build();
        }
    }

    static class AddressMapper implements Mapper<Address, AddressVO> {
        @Override
        public AddressVO domainToEntity(Address domain) {
            return AddressVO.builder()
                    .detailAddress(domain.getDetailAddress())
                    .streetAddress(domain.getStreetAddress())
                    .zipCode(domain.getZipCode())
                    .build();
        }

        @Override
        public Address entityToDomain(AddressVO entity) {
            return Address.builder()
                    .detailAddress(entity.getDetailAddress())
                    .streetAddress(entity.getStreetAddress())
                    .zipCode(entity.getZipCode())
                    .build();
        }
    }

    static class ParentMapper implements Mapper<ParentInfo, ParentVO> {
        @Override
        public ParentVO domainToEntity(ParentInfo domain) {
            return ParentVO.builder()
                    .birth(domain.getBirth())
                    .phone(domain.getPhone())
                    .name(domain.getName())
                    .relation(domain.getRelation())
                    .build();
        }

        @Override
        public ParentInfo entityToDomain(ParentVO entity) {
            return ParentInfo.builder()
                    .birth(entity.getBirth())
                    .phone(entity.getPhone())
                    .name(entity.getName())
                    .relation(entity.getRelation())
                    .build();
        }
    }
}
