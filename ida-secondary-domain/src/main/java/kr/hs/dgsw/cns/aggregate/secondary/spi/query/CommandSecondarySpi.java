package kr.hs.dgsw.cns.aggregate.secondary.spi.query;

import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;

public interface CommandSecondarySpi {
    void save(Secondary secondary);
}
