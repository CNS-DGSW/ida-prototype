package kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Semester;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendanceVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4255732487670626813L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    private short absence;

    private short tardiness;

    @Column(name = "early_leave")
    private short earlyLeave;

    private short skipped;

}
