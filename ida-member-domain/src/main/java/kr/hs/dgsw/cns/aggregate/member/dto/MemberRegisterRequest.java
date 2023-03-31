package kr.hs.dgsw.cns.aggregate.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class MemberRegisterRequest {

    private String email;

    private String password;

}
