package kr.hs.dgsw.cns.aggregate.applicant.entity.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeritVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5819639942548152697L;

    @Column(name = "merit_code")
    private String code;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MeritVO school = (MeritVO) obj;
        return Objects.equals(code, school.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public static MeritVO of(String code) {
        return new MeritVO(code);
    }
}
