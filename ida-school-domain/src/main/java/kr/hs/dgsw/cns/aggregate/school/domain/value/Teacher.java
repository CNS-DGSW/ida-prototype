package kr.hs.dgsw.cns.aggregate.school.domain.value;

import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Teacher {

    private String name;

    private PhoneNumber contact;

}
