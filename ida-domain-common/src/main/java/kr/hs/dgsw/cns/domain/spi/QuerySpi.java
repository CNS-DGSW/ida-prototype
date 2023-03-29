package kr.hs.dgsw.cns.domain.spi;

public interface QuerySpi<T, ID> {

    T findById(ID id);

}
