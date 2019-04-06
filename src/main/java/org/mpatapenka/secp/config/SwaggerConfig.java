package org.mpatapenka.secp.config;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.secp.SecpProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {
    private final SecpProperties properties;

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        SecpProperties.Contacts contacts = properties.getContacts();
        return new ApiInfoBuilder()
                .title("Simple E-Commerce Platform")
                .description("Simple E-Commerce Platform API for clients.")
                .version("1.0.0")
                .contact(new Contact(contacts.getAppName(), contacts.getSiteUrl(), contacts.getEmail()))
                .build();
    }
}