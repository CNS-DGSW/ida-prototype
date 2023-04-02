package kr.hs.dgsw.cns.aggregate.member.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@RequiredArgsConstructor
public class Password {

    private final String value;

    public Password changePassword(String newPassword) {
        return new Password(newPassword);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Password password = (Password) obj;
        return Objects.equals(value, password.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static Password of(String value) {
        return new Password(value);
    }
}
