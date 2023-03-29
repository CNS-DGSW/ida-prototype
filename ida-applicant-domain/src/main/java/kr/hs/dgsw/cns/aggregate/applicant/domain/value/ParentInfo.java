package kr.hs.dgsw.cns.aggregate.applicant.domain.value;

import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class ParentInfo {

    private final LocalDate birth;

    private PhoneNumber phone;

    private String name;

    private String relation;

}
