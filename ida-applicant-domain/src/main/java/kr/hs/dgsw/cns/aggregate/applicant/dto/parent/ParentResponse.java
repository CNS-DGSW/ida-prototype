package kr.hs.dgsw.cns.aggregate.applicant.dto.parent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Address;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.ParentInfo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ParentResponse {

    private final String name;
    private final String relation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate birth;

    private final String telephone;

    @JsonProperty("street_address")
    private final String streetAddress;

    @JsonProperty("detail_address")
    private final String detailAddress;

    @JsonProperty("zip_code")
    private final short zipCode;

    public ParentResponse(ParentInfo parentInfo, Address address) {
        if (parentInfo == null) {
            this.name = "";
            this.relation = "";
            this.birth = null;
            this.telephone = "";
        }
        else {
            this.name = parentInfo.getName();
            this.relation = parentInfo.getRelation();
            this.birth = parentInfo.getBirth();
            this.telephone = parentInfo.getPhone().getNumber();
        }

        if (address == null) {
            this.streetAddress = "";
            this.detailAddress = "";
            this.zipCode = -1;
        }
        else {
            this.streetAddress = address.getStreetAddress();
            this.detailAddress = address.getDetailAddress();
            this.zipCode = address.getZipCode();
        }
    }
}
