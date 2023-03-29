package kr.hs.dgsw.cns.aggregate.applicant.domain.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Address {

    private String detailAddress;

    private String streetAddress;

    private short zipCode;

}
