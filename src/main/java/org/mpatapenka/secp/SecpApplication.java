package org.mpatapenka.secp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecpProperties.class)
public class SecpApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecpApplication.class, args);
    }
}