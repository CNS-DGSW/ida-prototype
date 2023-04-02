package kr.hs.dgsw.cns.aggregate.applicant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Privacy;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Gender;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class UpdateInformationDto {

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private Gender gender;

    private String telephone;

    public Privacy toDomainVO() {
        return Privacy.builder()
                .name(name)
                .birth(birth)
                .gender(gender)
                .phone(PhoneNumber.of(telephone))
                .build();
    }

    public UpdateInformationDto(Privacy privacy) {
        this.name = privacy.getName();
        this.birth = privacy.getBirth();
        this.gender = privacy.getGender();
        this.telephone = privacy.getPhone().getNumber();
    }
}
