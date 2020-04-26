package com.aveco.awsproxy.configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    public static final Contact DEFAULT_CONTACT = new Contact("Petr Lastovka", "", "petr.lastovka@aveco.com");
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation",
        "Documentation for Aveco aws proxy", "0.1", "urn:tos",
        DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    private static final Set<String> DEFAULT_PRODUCERS_AND_CONSUMERS = new HashSet<>(
        Arrays.asList("application/json", "application/xml"));

    /**
     * Swagger documentation
     * 
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(DEFAULT_API_INFO)
            .produces(DEFAULT_PRODUCERS_AND_CONSUMERS);

    }
}
