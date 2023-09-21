package kr.hs.dgsw.cns.aggregate.applicant.dto.parent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Address;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.ParentInfo;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class UpdateParentRequest {

    private String name;
    private String relation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String telephone;

    // TODO: 프론트와 디자이너와 상의 하에 분리해야하는 것이 좋을 듯 함
    private String streetAddress;

    private String detailAddress;

    private short zipCode;

    public ParentInfo toParent() {
        return ParentInfo.builder()
                .name(name)
                .relation(relation)
                .birth(birth)
                .phone(PhoneNumber.of(telephone))
                .build();
    }

    public Address toAddress() {
        return Address.builder()
                .detailAddress(detailAddress)
                .streetAddress(streetAddress)
                .zipCode(zipCode)
                .build();
    }
}
