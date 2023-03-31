package kr.hs.dgsw.cns.aggregate.applicant.entity.value;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import kr.hs.dgsw.cns.aggregate.converter.PhoneNumberConverter;
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
public class ParentVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8043145527594742966L;

    @Column(name = "parent_birth")
    private LocalDate birth;

    @Column(name = "parent_phone")
    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phone;

    @Column(name = "parent_name")
    private String name;

    private String relation;

}
