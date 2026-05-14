package com.laolang.devstudio.framework.web.config;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 允许所有来源访问。生产环境中应指定具体的域名。
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        // 允许的方法
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // 允许的所有请求头
        config.setAllowedHeaders(Collections.singletonList("*"));
        // 是否允许发送Cookie信息
        config.setAllowCredentials(true);
        // 预检请求的有效期，单位为秒。
        config.setMaxAge(3600L);

        // 添加映射路径，“/**”表示对所有的路径都生效
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}