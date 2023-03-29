package kr.hs.dgsw.cns.aggregate.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;

@Converter
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {

    @Override
    public String convertToDatabaseColumn(PhoneNumber phoneNumber) {
        return phoneNumber == null ? null : phoneNumber.getNumber();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String contact) {
        return contact == null ? null : PhoneNumber.of(contact);
    }
}
