package kr.hs.dgsw.cns.aggregate.admission.entity.admission.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.AdmissionEntity;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.QAdmissionEntity;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.mapper.AdmissionMapper;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.QueryAdmissionSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdmissionQueryRepository implements QueryAdmissionSpi {

    private final JPAQueryFactory queryFactory;
    private final AdmissionMapper mapper;

    @Override
    public Optional<Admission> findById(Long id) {
        QAdmissionEntity admissionEntity = QAdmissionEntity.admissionEntity;
        AdmissionEntity entity = queryFactory.selectFrom(admissionEntity)
                .where(admissionEntity.id.eq(id))
                .fetchOne();
        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(mapper.entityToDomain(entity));
    }
}
