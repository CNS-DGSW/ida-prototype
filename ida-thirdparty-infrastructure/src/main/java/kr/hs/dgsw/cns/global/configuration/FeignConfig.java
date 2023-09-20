package kr.hs.dgsw.cns.global.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "kr.hs.dgsw.cns.global")
public class FeignConfig {
}
