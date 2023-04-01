package kr.hs.dgsw.cns.aggregate.admission.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Subject;

@Converter
public class SubjectConverter implements AttributeConverter<Subject, String> {

    @Override
    public String convertToDatabaseColumn(Subject subject) {
        return subject == null ? null : subject.getTopic();
    }

    @Override
    public Subject convertToEntityAttribute(String topic) {
        return topic == null ? null : Subject.of(topic);
    }
}
