package kr.hs.dgsw.cns.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class PhoneNumber {

    private final String number;

    public PhoneNumber changePhoneNumber(String contact) {
        return new PhoneNumber(contact);
    }

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
