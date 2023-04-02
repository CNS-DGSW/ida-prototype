package kr.hs.dgsw.cns.domain.spi;

import java.util.Optional;

/**
 * 도매인 레이어에서 쿼리(저수준 모듈)에서 사용될 쿼리 SPI
 * @param <T> 도메인 
 * @param <ID> 도메인의 식별 클래스
 */
public interface QuerySpi<T, ID> {

    /**
     * {@link ID}를 식별값으로 갖는 도메인 클래스를 찾습니다.
     * 만일 존재하지 않을 경우, {@link Optional#empty()}를
     * 반환합니다.
     * @param id 식별값
     * @return {@link ID}를 식별 타입으로 갖는 도메인 클래스를 반환합니다.
     */
    Optional<T> findById(ID id);

}
