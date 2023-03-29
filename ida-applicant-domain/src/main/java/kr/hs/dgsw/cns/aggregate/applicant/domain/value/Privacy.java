package kr.hs.dgsw.cns.aggregate.applicant.domain.value;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Gender;
import kr.hs.dgsw.cns.domain.Merit;
import kr.hs.dgsw.cns.domain.SchoolCode;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class Privacy {

    private final LocalDate birth;

    private final Gender gender;

    private final PhoneNumber phone;

    private SchoolCode school;

    private Address address;

    private ParentInfo parentInfo;

    private Merit merit;




}
