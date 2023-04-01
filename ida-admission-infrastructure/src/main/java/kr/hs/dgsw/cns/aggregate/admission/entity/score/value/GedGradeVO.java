package kr.hs.dgsw.cns.aggregate.admission.entity.score.value;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.admission.converter.PointConverter;
import kr.hs.dgsw.cns.aggregate.admission.converter.SubjectConverter;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Point;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Subject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GedGradeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5520222159156087172L;

    @Column(nullable = false)
    @Convert(converter = SubjectConverter.class)
    private Subject subject;

    @Column(nullable = false)
    @Convert(converter = PointConverter.class)
    private Point point;

}
