package kr.hs.dgsw.cns.aggregate.entity.value;

import jakarta.persistence.Embeddable;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5776368143260134080L;

    private String name;

    private PhoneNumber contact;

}
