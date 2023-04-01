package kr.hs.dgsw.cns.aggregate.admission.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Point;

@Converter
public class PointConverter implements AttributeConverter<Point, Short> {

    @Override
    public Short convertToDatabaseColumn(Point point) {
        return point == null ? null : point.getValue();
    }

    @Override
    public Point convertToEntityAttribute(Short value) {
        return value == null ? null : Point.of(value);
    }
}
