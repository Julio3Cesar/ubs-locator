package br.com.test.ubs.configurations;

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
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.test.ubs.controllers"))
                .paths(PathSelectors.ant("/api/v1/*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple REST API to handle ubs location.")
                .description("The system consumes all city-supplied ubs and " +
                        "provides support functions for its management.")
                .version("1.0.0")
                .contact(new Contact("Julio Cesar",
                        "https://www.linkedin.com/in/julio-cesar-souza-59b7b3143/",
                        "bob-cesar@hotmail.com.br"))
                .build();
    }
}
