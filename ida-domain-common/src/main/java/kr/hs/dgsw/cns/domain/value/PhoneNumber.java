package kr.hs.dgsw.cns.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * 여러 애그리거트에서 공통적으로 전화번호라는 필드가 사용되기에
 * 따로 Value-Class로 설계된 클래스 <br>
 * 해당 클래스는 Value-Class이므로 불변성을 가지고 있습니다.
 */
@Getter
@RequiredArgsConstructor
public class PhoneNumber {

    private final String number;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PhoneNumber phone = (PhoneNumber) obj;
        return Objects.equals(number, phone.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public static PhoneNumber of(String number) {
        return new PhoneNumber(number);
    }
}
