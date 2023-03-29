package kr.hs.dgsw.cns.global.embedd;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SchoolCode implements Serializable {

    @Serial
    private static final long serialVersionUID = 6409566597722370789L;

    @Column(name = "school_code")
    private int code;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SchoolCode school = (SchoolCode) obj;
        return code == school.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public static SchoolCode of(int code) {
        return new SchoolCode(code);
    }
}
