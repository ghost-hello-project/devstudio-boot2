package com.laolang.devstudio.framework.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "devstudio")
public class DevStudioConfig {

    private String author;

}
