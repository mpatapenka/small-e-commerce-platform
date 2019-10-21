package by.underwear.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(UnderwearShopProperties.class)
public class UnderwearShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnderwearShopApplication.class, args);
    }
}