package kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Point;
import kr.hs.dgsw.cns.aggregate.converter.PointConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Volunteer implements Serializable {

    @Serial
    private static final long serialVersionUID = -6186140834280014288L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Convert(converter = PointConverter.class)
    private Point point;

}
