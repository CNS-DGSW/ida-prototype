package kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade;

import jakarta.persistence.*;
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
public class LeaderShipVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1898856673170033661L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    private boolean check;

}
