package kr.hs.dgsw.cns.aggregate.applicant.domain;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Privacy;
import kr.hs.dgsw.cns.domain.MemberId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Applicant {

    private final MemberId id;

    private Privacy privacy;

    public void updatePrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

}
