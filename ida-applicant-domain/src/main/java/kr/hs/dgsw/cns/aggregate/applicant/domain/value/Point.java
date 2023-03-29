package kr.hs.dgsw.cns.aggregate.applicant.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class Point {

    private final short value;

    public Point multiple(short value) {
        return new Point((short) (this.value * value));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return Objects.equals(value, point.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static Point of(short value) {
        return new Point(value);
    }
}
