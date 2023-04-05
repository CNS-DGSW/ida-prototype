package kr.hs.dgsw.cns.aggregate.applicant.dto.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Privacy;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Gender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class InformationResponse {

    private final String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate birth;

    private final Gender gender;

    private final String telephone;

    public InformationResponse(Privacy privacy) {
        if (privacy == null) {
            this.name = "";
            this.birth = null;
            this.gender = null;
            this.telephone = "";
        }
        else {
            this.name = privacy.getName();
            this.birth = privacy.getBirth();
            this.gender = privacy.getGender();
            this.telephone = privacy.getPhone().getNumber();
        }
    }
}
