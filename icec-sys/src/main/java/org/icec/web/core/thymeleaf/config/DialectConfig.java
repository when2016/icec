package org.icec.web.core.thymeleaf.config;

import org.icec.web.core.thymeleaf.dict.DictDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DialectConfig {

	@Bean
    public DictDialect getDictDialect() {
        return new DictDialect();
    }
}
