package kr.hs.dgsw.cns.aggregate.secondary.dao;

import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import kr.hs.dgsw.cns.aggregate.secondary.entity.SecondaryScoreEntity;
import kr.hs.dgsw.cns.aggregate.secondary.mapper.SecondaryScoreMapper;
import kr.hs.dgsw.cns.aggregate.secondary.spi.query.CommandSecondarySpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SecondaryCommandRepository implements CommandSecondarySpi {
    private final SecondaryScoreMapper mapper;
    private final SecondaryRepository secondaryRepository;

    @Override
    public void save(Secondary secondary) {
        SecondaryScoreEntity entity = mapper.domainToEntity(secondary);
        secondaryRepository.save(entity);
    }
}
