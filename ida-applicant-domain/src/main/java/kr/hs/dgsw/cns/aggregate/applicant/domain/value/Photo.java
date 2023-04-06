package kr.hs.dgsw.cns.aggregate.applicant.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class Photo {

    private final String filename;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Photo phone = (Photo) obj;
        return Objects.equals(filename, phone.getFilename());
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename);
    }

    public static Photo of(String filename) {
        return new Photo(filename);
    }
}
