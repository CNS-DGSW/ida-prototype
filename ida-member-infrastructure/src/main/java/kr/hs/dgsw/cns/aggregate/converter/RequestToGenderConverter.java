package kr.hs.dgsw.cns.aggregate.converter;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Gender;
import org.springframework.core.convert.converter.Converter;

public class RequestToGenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        return Gender.valueOf(source.toUpperCase());
    }
}
