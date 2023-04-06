package kr.hs.dgsw.cns.aggregate.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Photo;

@Converter
public class PhotoConverter implements AttributeConverter<Photo, String> {

    @Override
    public String convertToDatabaseColumn(Photo photo) {
        return photo == null ? null : photo.getFilename();
    }

    @Override
    public Photo convertToEntityAttribute(String filename) {
        return filename == null ? null : Photo.of(filename);
    }
}
