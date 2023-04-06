package kr.hs.dgsw.cns.aggregate.applicant.entity.value;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Photo;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Gender;
import kr.hs.dgsw.cns.aggregate.converter.PhoneNumberConverter;
import kr.hs.dgsw.cns.aggregate.converter.PhotoConverter;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
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

    private String name;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phone;

    @Column(name = "id_photo")
    @Convert(converter = PhotoConverter.class)
    private Photo idPhoto;

    @Embedded
    private SchoolCodeVO school;

    @Embedded
    private AddressVO address;

    @Embedded
    private ParentVO parent;

    @Embedded
    private MeritVO merit;

}
