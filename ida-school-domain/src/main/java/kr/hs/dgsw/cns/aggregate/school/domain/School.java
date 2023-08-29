package kr.hs.dgsw.cns.aggregate.school.domain;

import kr.hs.dgsw.cns.aggregate.school.domain.value.SchoolInfo;
import kr.hs.dgsw.cns.aggregate.school.domain.value.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class School {
    private SchoolInfo schoolInfo;

    private Teacher teacher;
}
