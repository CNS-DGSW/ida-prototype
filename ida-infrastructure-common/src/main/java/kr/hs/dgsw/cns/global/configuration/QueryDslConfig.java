package kr.hs.dgsw.cns.global.configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QueryDSL를 설정하는 설정 클래스 <br>
 * 더 이상 수정하지 않는 설정 클래스이기에 설계가 바뀌지 않는 한,
 * 해당 클래스를 수정하여서는 안됩니다.
 */
@Configuration
public class QueryDslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
