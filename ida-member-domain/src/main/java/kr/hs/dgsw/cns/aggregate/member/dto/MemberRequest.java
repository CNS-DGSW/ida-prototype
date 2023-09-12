package kr.hs.dgsw.cns.aggregate.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class MemberRequest {

    private String contact;

    private String password;

}
