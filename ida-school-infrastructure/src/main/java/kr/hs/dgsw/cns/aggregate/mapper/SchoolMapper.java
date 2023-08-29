package kr.hs.dgsw.cns.aggregate.mapper;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Privacy;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.SchoolCode;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.PersonalInformation;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.SchoolCodeVO;
import kr.hs.dgsw.cns.aggregate.entity.SchoolEntity;
import kr.hs.dgsw.cns.aggregate.entity.value.SchoolInfoVO;
import kr.hs.dgsw.cns.aggregate.entity.value.TeacherVO;
import kr.hs.dgsw.cns.aggregate.school.domain.School;
import kr.hs.dgsw.cns.aggregate.school.domain.value.SchoolInfo;
import kr.hs.dgsw.cns.aggregate.school.domain.value.Teacher;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import kr.hs.dgsw.cns.global.util.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolMapper implements Mapper<School, SchoolEntity> {

    private static final SchoolInfoMapper SCHOOL_INFO_MAPPER = new SchoolInfoMapper();
    private static final TeacherMapper TEACHER_MAPPER= new TeacherMapper();


    @Override
    public SchoolEntity domainToEntity(School domain) {
        return SchoolEntity.builder()
                .schoolInfo(SCHOOL_INFO_MAPPER.domainToEntity(domain.getSchoolInfo()))
                .teacher(TEACHER_MAPPER.domainToEntity(domain.getTeacher()))
                .build();
    }

    @Override
    public School entityToDomain(SchoolEntity entity) {
        return School.builder()
                .schoolInfo(SCHOOL_INFO_MAPPER.entityToDomain(entity.getSchoolInfo()))
                .teacher(TEACHER_MAPPER.entityToDomain(entity.getTeacher()))
                .build();
    }

    static class SchoolInfoMapper implements Mapper<SchoolInfo, SchoolInfoVO> {

        @Override
        public SchoolInfoVO domainToEntity(SchoolInfo domain) {
            return SchoolInfoVO.builder()
                    .location(domain.getLocation())
                    .schoolCode(MapperUtils.isNull(domain.getSchoolCode()) ? null : SchoolCodeVO.of(domain.getSchoolCode().getCode()))
                    .contact(domain.getContact())
                    .schoolName(domain.getSchoolName())
                    .graduateYear(domain.getGraduateYear())
                    .build();
        }

        @Override
        public SchoolInfo entityToDomain(SchoolInfoVO entity) {
            return SchoolInfo.builder()
                    .location(entity.getLocation())
                    .schoolCode(MapperUtils.isNull(entity.getSchoolCode()) ? null : new SchoolCode(entity.getSchoolCode().getCode()))
                    .contact(entity.getContact())
                    .schoolName(entity.getSchoolName())
                    .graduateYear(entity.getGraduateYear())
                    .build();
        }
    }

    static class TeacherMapper implements Mapper<Teacher, TeacherVO> {

        @Override
        public TeacherVO domainToEntity(Teacher domain) {
            return TeacherVO.builder()
                    .name(domain.getName())
                    .contact(domain.getContact())
                    .build();
        }

        @Override
        public Teacher entityToDomain(TeacherVO entity) {
            return Teacher.builder()
                    .name(entity.getName())
                    .contact(entity.getContact())
                    .build();
        }
    }


}
