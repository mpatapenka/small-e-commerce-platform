package org.mpatapenka.ssp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SspProperties.class)
public class SspApplication {
    public static void main(String[] args) {
        SpringApplication.run(SspApplication.class, args);
    }
}