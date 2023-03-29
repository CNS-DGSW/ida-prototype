package kr.hs.dgsw.cns.aggregate.member.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.member.converter.PasswordConverter;
import kr.hs.dgsw.cns.aggregate.member.domain.value.Password;
import kr.hs.dgsw.cns.aggregate.member.domain.value.Role;
import kr.hs.dgsw.cns.global.embedd.MemberId;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "member")
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class MemberEntity {

    @EmbeddedId
    private MemberId id;

    private String email;

    private String name;

    @Convert(converter = PasswordConverter.class)
    private Password password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
