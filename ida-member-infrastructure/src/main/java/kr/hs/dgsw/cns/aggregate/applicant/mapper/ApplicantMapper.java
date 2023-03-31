package kr.hs.dgsw.cns.aggregate.applicant.mapper;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.applicant.domain.GedScore;
import kr.hs.dgsw.cns.aggregate.applicant.domain.SchoolScore;
import kr.hs.dgsw.cns.aggregate.applicant.domain.Score;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Address;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.ParentInfo;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Privacy;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.AdmissionType;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade.AttendancePoint;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade.GedGrade;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade.SchoolGrade;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade.VolunteerPoint;
import kr.hs.dgsw.cns.aggregate.applicant.entity.AbstractScore;
import kr.hs.dgsw.cns.aggregate.applicant.entity.ApplicantEntity;
import kr.hs.dgsw.cns.aggregate.applicant.entity.QualificationScoreEntity;
import kr.hs.dgsw.cns.aggregate.applicant.entity.SchoolScoreEntity;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.AddressVO;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.ParentVO;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.PersonalInformation;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.AttendanceVO;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.GedGradeVO;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.SchoolGradeVO;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.VolunteerVO;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.domain.SchoolCode;
import kr.hs.dgsw.cns.global.embedd.EmbeddedMemberId;
import kr.hs.dgsw.cns.global.embedd.SchoolCodeVO;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import kr.hs.dgsw.cns.global.util.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicantMapper implements Mapper<Applicant, ApplicantEntity> {

    private static final InformationMapper INFORMATION_MAPPER = new InformationMapper();
    private static final ScoreMapper SCORE_MAPPER = new ScoreMapper();
    private static final AddressMapper ADDRESS_MAPPER = new AddressMapper();
    private static final ParentMapper PARENT_MAPPER = new ParentMapper();

    @Override
    public ApplicantEntity domainToEntity(Applicant domain) {
        return ApplicantEntity.builder()
                .embeddedMemberId(EmbeddedMemberId.of(domain.getId().getId()))
                .information(MapperUtils.convertToEntityIsNull(domain.getPrivacy(), INFORMATION_MAPPER))
                .score(MapperUtils.convertToEntityIsNull(domain.getScore(), SCORE_MAPPER))
                .type(MapperUtils.isNull(domain.getAdmissionType()) ? AdmissionType.NONE : domain.getAdmissionType())
                .build();
    }

    @Override
    public Applicant entityToDomain(ApplicantEntity entity) {
        return Applicant.builder()
                .id(new MemberId(entity.getEmbeddedMemberId().getId()))
                .privacy(MapperUtils.convertToDomainIsNull(entity.getInformation(), INFORMATION_MAPPER))
                .score(MapperUtils.convertToDomainIsNull(entity.getScore(), SCORE_MAPPER))
                .admissionType(MapperUtils.isNull(entity.getType()) ? AdmissionType.NONE : entity.getType())
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

    static class ScoreMapper implements Mapper<Score, AbstractScore> {

        @Override
        public AbstractScore domainToEntity(Score domain) {
            if (domain instanceof GedScore) {
                return new QualificationScoreEntity(domain.getId(), (((GedScore) domain).getGedScores())
                        .stream().map(gedGrade -> new GedGradeVO(gedGrade.getSubject(), gedGrade.getPoint()))
                        .collect(Collectors.toList()));
            }

            SchoolScore score = (SchoolScore) domain;

            List<SchoolGradeVO> schoolGradeVOS = score.getSchoolGrades().stream()
                    .map(schoolGrade -> new SchoolGradeVO(schoolGrade.getGrade(),
                            schoolGrade.getSemester(), schoolGrade.getSubject(), schoolGrade.isDoubled(),
                            schoolGrade.getPoint())).toList();
            List<AttendanceVO> attendanceVOS = score.getAttendancePoints().stream()
                    .map(ap -> new AttendanceVO(ap.getGrade(), ap.getSemester(), ap.getAbsence(),
                            ap.getTardiness(), ap.getEarlyLeave(), ap.getSkipped())).toList();
            List<VolunteerVO> volunteerVOS = score.getVolunteerPoints().stream()
                    .map(vp -> new VolunteerVO(vp.getGrade(), vp.getPoint()))
                    .toList();

            return new SchoolScoreEntity(domain.getId(), schoolGradeVOS, attendanceVOS, volunteerVOS,
                    score.getPrize());
        }

        @Override
        public Score entityToDomain(AbstractScore entity) {
            if (entity instanceof QualificationScoreEntity score) {
                return new GedScore(score.getId(), score.getGedGrades().stream()
                        .map(gg -> new GedGrade(gg.getSubject(), gg.getPoint())).toList());
            }

            SchoolScoreEntity score = (SchoolScoreEntity) entity;
            List<SchoolGrade> schoolGrades
                    = score.getSchoolGrades().stream().map(sg ->
                    new SchoolGrade(
                            sg.getGrade(), sg.getSemester(), sg.getSubject(), sg.isDoubled(), sg.getPoint()
                    )).toList();
            List<AttendancePoint> attendancePoints = score.getAttendances().stream()
                    .map(a -> new AttendancePoint(
                            a.getGrade(), a.getSemester(), a.getAbsence(), a.getTardiness(), a.getEarlyLeave(),
                            a.getSkipped())).toList();
            List<VolunteerPoint> volunteerPoints = score.getVolunteers().stream()
                    .map(v -> new VolunteerPoint(v.getGrade(), v.getPoint()))
                    .toList();
            return new SchoolScore(
                    entity.getId(), schoolGrades, attendancePoints, volunteerPoints, score.getPrize()
            );
        }
    }
}
