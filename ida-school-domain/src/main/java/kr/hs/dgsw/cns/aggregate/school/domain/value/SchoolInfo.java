package kr.hs.dgsw.cns.aggregate.school.domain.value;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.SchoolCode;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SchoolInfo {

    private String location;

    private SchoolCode schoolCode;

    private PhoneNumber contact;

    private String schoolName;

    private String graduateYear;

}
