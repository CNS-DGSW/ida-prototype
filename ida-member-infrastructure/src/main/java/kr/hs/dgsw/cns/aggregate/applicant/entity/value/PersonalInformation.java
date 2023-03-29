package kr.hs.dgsw.cns.aggregate.applicant.entity.value;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Gender;
import kr.hs.dgsw.cns.aggregate.converter.PhoneNumberConverter;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import kr.hs.dgsw.cns.global.embedd.Merit;
import kr.hs.dgsw.cns.global.embedd.SchoolCode;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@ToString
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalInformation implements Serializable {
    @Serial
    private static final long serialVersionUID = 5223215781809764441L;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phone;

    @Embedded
    private SchoolCode school;

    @Embedded
    private Address address;

    @Embedded
    private Parent parent;

    @Embedded
    private Merit merit;

}
