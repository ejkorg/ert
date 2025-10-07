package com.onsemi.cim.apps.exensioreftables.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ExensioRefTablesWsApplication extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(ExensioRefTablesWsApplication.class);

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(ExensioRefTablesWsApplication.class);
        sa.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ExensioRefTablesWsApplication.class);
    }
}
