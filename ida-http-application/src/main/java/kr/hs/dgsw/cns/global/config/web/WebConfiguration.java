package kr.hs.dgsw.cns.global.config.web;

import kr.hs.dgsw.cns.aggregate.converter.RequestToGenderConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new RequestToGenderConverter());
    }
}
