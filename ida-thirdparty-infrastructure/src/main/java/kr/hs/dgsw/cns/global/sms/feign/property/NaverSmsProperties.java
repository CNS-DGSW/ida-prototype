package kr.hs.dgsw.cns.global.sms.feign.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("external.sms")
public class NaverSmsProperties {
    private String serviceId;
    private String accessKey;
    private String secretKey;
    private String from;
}
