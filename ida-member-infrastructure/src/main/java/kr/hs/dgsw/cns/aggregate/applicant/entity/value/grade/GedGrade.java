package kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Point;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Subject;
import kr.hs.dgsw.cns.aggregate.converter.PointConverter;
import kr.hs.dgsw.cns.aggregate.converter.SubjectConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GedGrade implements Serializable {

    @Serial
    private static final long serialVersionUID = -5520222159156087172L;

    @Column(nullable = false)
    @Convert(converter = SubjectConverter.class)
    private Subject subject;

    @Column(nullable = false)
    @Convert(converter = PointConverter.class)
    private Point point;

}
