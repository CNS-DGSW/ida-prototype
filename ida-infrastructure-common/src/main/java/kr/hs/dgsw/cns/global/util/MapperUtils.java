package kr.hs.dgsw.cns.global.util;

import kr.hs.dgsw.cns.global.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapperUtils {

    public static <T, D> D convertToDomainIsNull(T convertTo, Mapper<D, T> domainMapper) {
        return (convertTo == null) ? null : domainMapper.entityToDomain(convertTo);
    }

    public static <T, D> T convertToEntityIsNull(D convertTo, Mapper<D, T> entityMapper) {
        return (convertTo == null) ? null : entityMapper.domainToEntity(convertTo);
    }

    public static <T> boolean isNull(T t) {
        return t == null;
    }
}
