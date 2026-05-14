package com.laolang.devstudio.framework.web.config;

import com.laolang.devstudio.framework.common.util.jackson.module.BigDecimalModule;
import com.laolang.devstudio.framework.common.util.jackson.module.Jdk8TimeModule;
import com.laolang.devstudio.framework.common.util.jackson.module.LongModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.modulesToInstall(new BigDecimalModule(), new LongModule(), new Jdk8TimeModule());
    }

}
