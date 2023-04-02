package kr.hs.dgsw.cns.global.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwt")
public class TokenProperties {

    private String secret;
    private String refresh;
    private long expireMillis;

}
