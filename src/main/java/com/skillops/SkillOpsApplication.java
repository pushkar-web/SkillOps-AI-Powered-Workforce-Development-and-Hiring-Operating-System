package com.skillops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SkillOpsApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(SkillOpsApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SkillOpsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SkillOpsApplication.class, args);
        log.info("SkillOps application started. Swagger UI: http://localhost:8080/swagger-ui.html");
    }
}
