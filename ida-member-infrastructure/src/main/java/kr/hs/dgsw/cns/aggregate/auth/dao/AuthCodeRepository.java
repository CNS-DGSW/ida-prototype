package kr.hs.dgsw.cns.aggregate.auth.dao;

import kr.hs.dgsw.cns.aggregate.auth.entity.AuthCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeRepository extends CrudRepository<AuthCodeEntity, String> {
    AuthCodeEntity findByCode(String code);
}
