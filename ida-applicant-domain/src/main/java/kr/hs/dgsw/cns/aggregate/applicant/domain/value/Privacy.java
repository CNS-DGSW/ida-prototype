package kr.hs.dgsw.cns.aggregate.applicant.domain.value;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Gender;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class Privacy {

    private final String name;

    private final LocalDate birth;

    private final Gender gender;

    private final PhoneNumber phone;

    private SchoolCode school;

    private Address address;

    private ParentInfo parentInfo;

    private Merit merit;

    private Photo photo;

    public Privacy() {
        this.name = null;
        this.birth = null;
        this.gender = null;
        this.phone = null;
    }

    public void updateParentInfo(ParentInfo parentInfo) {
        this.parentInfo = parentInfo;
    }

    public void updateAddress(Address address) {
        this.address = address;
    }

    public void updateIdPhoto(Photo filename) {
        this.photo = filename;
    }
}
