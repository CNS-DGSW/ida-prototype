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
public class EmbeddedMemberId implements Serializable {

    @Serial
    private static final long serialVersionUID = -7305105972131878927L;

    @Column(name = "member_id")
    private Long id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmbeddedMemberId embeddedMemberId = (EmbeddedMemberId) obj;
        return Objects.equals(id, embeddedMemberId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static EmbeddedMemberId of(long id) {
        return new EmbeddedMemberId(id);
    }
}
