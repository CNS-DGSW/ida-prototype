package kr.hs.dgsw.cns.aggregate.admission.entity.score.value;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.admission.converter.PointConverter;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Point;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Grade;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolunteerVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6186140834280014288L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Convert(converter = PointConverter.class)
    private Point point;

}
