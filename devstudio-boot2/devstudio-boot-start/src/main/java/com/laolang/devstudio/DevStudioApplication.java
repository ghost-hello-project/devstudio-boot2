package com.laolang.devstudio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DevStudioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevStudioApplication.class, args);
        log.info("devstudio is running...");
    }

}
