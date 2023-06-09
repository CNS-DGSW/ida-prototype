package kr.hs.dgsw.cns.aggregate.member.domain;

import kr.hs.dgsw.cns.aggregate.member.domain.value.Role;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.aggregate.member.domain.value.Password;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private final MemberId id;

    private final String email;

    private Password password;

    private Role role;

    public void changePassword(Password newPassword) {
        changePassword(newPassword.getValue());
    }

    public void changePassword(String newPassword) {
        this.password = password.changePassword(newPassword);
    }

}
