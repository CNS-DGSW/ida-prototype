package kr.hs.dgsw.cns.aggregate.applicant.mapper;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.applicant.domain.GedScore;
import kr.hs.dgsw.cns.aggregate.applicant.domain.Score;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Address;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.ParentInfo;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Privacy;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.AdmissionType;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade.AttendancePoint;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade.VolunteerPoint;
import kr.hs.dgsw.cns.aggregate.applicant.entity.ApplicantEntity;
import kr.hs.dgsw.cns.aggregate.applicant.entity.QualificationScore;
import kr.hs.dgsw.cns.aggregate.applicant.entity.SchoolScore;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.Parent;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.PersonalInformation;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.Attendance;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.GedGrade;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.SchoolGrade;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.Volunteer;
import kr.hs.dgsw.cns.global.embedd.MemberId;
import kr.hs.dgsw.cns.global.embedd.SchoolCode;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import kr.hs.dgsw.cns.global.util.IdGenerator;
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
                .memberId(MemberId.of(domain.getId().getId()))
                .information(INFORMATION_MAPPER.domainToEntity(domain.getPrivacy()))
                .score(
                        IdGenerator.isNull(domain.getScore()) ? null : SCORE_MAPPER.domainToEntity(domain.getScore())
                )
                .type(IdGenerator.isNull(domain.getAdmissionType()) ? AdmissionType.NONE : domain.getAdmissionType())
                .build();
    }

    @Override
    public Applicant entityToDomain(ApplicantEntity entity) {
        return Applicant.builder()
                .id(new kr.hs.dgsw.cns.domain.MemberId(entity.getMemberId().getId()))
                .privacy(INFORMATION_MAPPER.entityToDomain(entity.getInformation()))
                .score(SCORE_MAPPER.entityToDomain(entity.getScore()))
                .admissionType(entity.getType())
                .build();
    }

    static class InformationMapper implements Mapper<Privacy, PersonalInformation> {

        @Override
        public PersonalInformation domainToEntity(Privacy domain) {
            return PersonalInformation.builder()
                    .birth(domain.getBirth())
                    .gender(domain.getGender())
                    .phone(domain.getPhone())
                    .school(
                            IdGenerator.isNull(domain.getSchool()) ? null : SchoolCode.of(domain.getSchool().getCode())
                    )
                    .address(
                            IdGenerator.isNull(domain.getAddress()) ? null : ADDRESS_MAPPER.domainToEntity(domain.getAddress())
                    )
                    .parent(
                            IdGenerator.isNull(domain.getParentInfo()) ? null : PARENT_MAPPER.domainToEntity(domain.getParentInfo())
                    )
                    .build();
        }

        @Override
        public Privacy entityToDomain(PersonalInformation entity) {
            return Privacy.builder()
                    .birth(entity.getBirth())
                    .gender(entity.getGender())
                    .phone(entity.getPhone())
                    .school(new kr.hs.dgsw.cns.domain.SchoolCode(entity.getSchool().getCode()))
                    .address(ADDRESS_MAPPER.entityToDomain(entity.getAddress()))
                    .parentInfo(PARENT_MAPPER.entityToDomain(entity.getParent()))
                    .build();
        }
    }

    static class AddressMapper implements Mapper<Address, kr.hs.dgsw.cns.aggregate.applicant.entity.value.Address> {
        @Override
        public kr.hs.dgsw.cns.aggregate.applicant.entity.value.Address domainToEntity(Address domain) {
            return kr.hs.dgsw.cns.aggregate.applicant.entity.value.Address.builder()
                    .detailAddress(domain.getDetailAddress())
                    .streetAddress(domain.getStreetAddress())
                    .zipCode(domain.getZipCode())
                    .build();
        }

        @Override
        public Address entityToDomain(kr.hs.dgsw.cns.aggregate.applicant.entity.value.Address entity) {
            return Address.builder()
                    .detailAddress(entity.getDetailAddress())
                    .streetAddress(entity.getStreetAddress())
                    .zipCode(entity.getZipCode())
                    .build();
        }
    }

    static class ParentMapper implements Mapper<ParentInfo, Parent> {
        @Override
        public Parent domainToEntity(ParentInfo domain) {
            return Parent.builder()
                    .birth(domain.getBirth())
                    .phone(domain.getPhone())
                    .name(domain.getName())
                    .relation(domain.getRelation())
                    .build();
        }

        @Override
        public ParentInfo entityToDomain(Parent entity) {
            return ParentInfo.builder()
                    .birth(entity.getBirth())
                    .phone(entity.getPhone())
                    .name(entity.getName())
                    .relation(entity.getRelation())
                    .build();
        }
    }

    static class ScoreMapper implements Mapper<Score, kr.hs.dgsw.cns.aggregate.applicant.entity.Score> {

        @Override
        public kr.hs.dgsw.cns.aggregate.applicant.entity.Score domainToEntity(Score domain) {
            if (domain instanceof GedScore) {
                return new QualificationScore(domain.getId(), (((GedScore) domain).getGedScores())
                        .stream().map(gedGrade -> new GedGrade(gedGrade.getSubject(), gedGrade.getPoint()))
                        .collect(Collectors.toList()));
            }

            kr.hs.dgsw.cns.aggregate.applicant.domain.SchoolScore score
                    = (kr.hs.dgsw.cns.aggregate.applicant.domain.SchoolScore) domain;

            List<SchoolGrade> schoolGrades = score.getSchoolGrades().stream()
                    .map(schoolGrade -> new SchoolGrade(schoolGrade.getGrade(),
                            schoolGrade.getSemester(), schoolGrade.getSubject(), schoolGrade.isDoubled(),
                            schoolGrade.getPoint())).toList();
            List<Attendance> attendances = score.getAttendancePoints().stream()
                    .map(ap -> new Attendance(ap.getGrade(), ap.getSemester(), ap.getAbsence(),
                            ap.getTardiness(), ap.getEarlyLeave(), ap.getSkipped())).toList();
            List<Volunteer> volunteers = score.getVolunteerPoints().stream()
                    .map(vp -> new Volunteer(vp.getGrade(), vp.getPoint()))
                    .toList();

            return new SchoolScore(domain.getId(), schoolGrades, attendances, volunteers,
                    score.getPrize());
        }

        @Override
        public Score entityToDomain(kr.hs.dgsw.cns.aggregate.applicant.entity.Score entity) {
            if (entity instanceof QualificationScore score) {
                return new GedScore(score.getId(), score.getGedGrades().stream()
                        .map(gg -> new kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade
                                .GedGrade(gg.getSubject(), gg.getPoint())).toList());
            }

            SchoolScore score = (SchoolScore) entity;
            List<kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade.SchoolGrade> schoolGrades
                    = score.getSchoolGrades().stream().map(sg ->
                    new kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade.SchoolGrade(
                            sg.getGrade(), sg.getSemester(), sg.getSubject(), sg.isDoubled(), sg.getPoint()
                    )).toList();
            List<AttendancePoint> attendancePoints = score.getAttendances().stream()
                    .map(a -> new AttendancePoint(
                            a.getGrade(), a.getSemester(), a.getAbsence(), a.getTardiness(), a.getEarlyLeave(),
                            a.getSkipped())).toList();
            List<VolunteerPoint> volunteerPoints = score.getVolunteers().stream()
                    .map(v -> new VolunteerPoint(v.getGrade(), v.getPoint()))
                    .toList();
            return new kr.hs.dgsw.cns.aggregate.applicant.domain.SchoolScore(
                    entity.getId(), schoolGrades, attendancePoints, volunteerPoints, score.getPrize()
            );
        }
    }

}
