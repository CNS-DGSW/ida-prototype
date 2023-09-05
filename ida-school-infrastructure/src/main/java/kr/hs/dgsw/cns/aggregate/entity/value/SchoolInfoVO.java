package kr.hs.dgsw.cns.aggregate.entity.value;

import jakarta.persistence.Embeddable;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.SchoolCodeVO;
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
@AllArgsConstructor @NoArgsConstructor
public class SchoolInfoVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3706604469320070896L;

    private String location;

    private SchoolCodeVO schoolCode;

    private PhoneNumber contact;

    private String schoolName;

    private String graduateYear;

}
