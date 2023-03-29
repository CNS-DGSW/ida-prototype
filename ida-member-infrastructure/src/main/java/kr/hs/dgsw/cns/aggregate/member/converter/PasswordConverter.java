package kr.hs.dgsw.cns.aggregate.member.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.hs.dgsw.cns.aggregate.member.domain.value.Password;

@Converter
public class PasswordConverter implements AttributeConverter<Password, String> {

    @Override
    public String convertToDatabaseColumn(Password password) {
        return password == null ? null : password.getValue();
    }

    @Override
    public Password convertToEntityAttribute(String value) {
        return value == null ? null : new Password(value);
    }
}
