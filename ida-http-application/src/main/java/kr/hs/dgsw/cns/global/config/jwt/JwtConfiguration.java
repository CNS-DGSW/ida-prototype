package kr.hs.dgsw.cns.global.config.jwt;

import kr.hs.dgsw.cns.global.property.JwtProperties;
import kr.hs.dgsw.cns.global.property.TokenProperties;
import kr.hs.dgsw.cns.global.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtConfiguration {

    private final TokenProperties tokenProperties;

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(new JwtProperties(
                tokenProperties.getSecret(), tokenProperties.getRefresh(),
                tokenProperties.getExpireMillis()
        ));
    }
}
