package kr.hs.dgsw.cns.aggregate.applicant.entity.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@ToString
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 6069439922948968201L;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "zip_code")
    private short zipCode;

}
