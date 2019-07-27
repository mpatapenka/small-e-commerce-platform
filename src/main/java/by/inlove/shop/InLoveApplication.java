package by.inlove.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(InLoveProperties.class)
public class InLoveApplication {
    public static void main(String[] args) {
        SpringApplication.run(InLoveApplication.class, args);
    }
}