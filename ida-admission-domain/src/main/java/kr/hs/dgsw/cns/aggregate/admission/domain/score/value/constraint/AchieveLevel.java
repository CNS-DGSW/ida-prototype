package kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint;

import lombok.Getter;

@Getter
public enum AchieveLevel {
    A(5),
    B(4),
    C(3),
    D(2),
    E(1),
    NONE(0)
    ;

    private final int value;

    AchieveLevel(int value) {
        this.value = value;
    }
}
