package kr.hs.dgsw.cns.domain.spi;

import java.util.Optional;

public interface QuerySpi<T, ID> {

    Optional<T> findById(ID id);

}
