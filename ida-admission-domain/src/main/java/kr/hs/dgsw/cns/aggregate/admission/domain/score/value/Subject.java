package kr.hs.dgsw.cns.aggregate.admission.domain.score.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class Subject {

    private final String topic;

    public Subject changeTopic(String topic) {
        return new Subject(topic);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Subject subject = (Subject) obj;
        return Objects.equals(topic, subject.getTopic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic);
    }

    public static Subject of(String topic) {
        return new Subject(topic);
    }
}
