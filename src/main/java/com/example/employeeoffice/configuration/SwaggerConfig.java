package com.example.employeeoffice.configuration;

import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.GroupedOpenApi;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Office",
                description = "There is a prototype of the BackEnd Employees Office's Core Services data. <br />" +
                        "Data consist of addresses, authorities, departments, employees, events, personal infos, roles," +
                        "vacancies and work schedules.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Anna Leonova",
                        email = "annaleonova88888@gmail.com",
                        url = "https://github.com/AnnaLeonova1808"
                )
        )
)
@Configuration
public class SwaggerConfig {

    @Value("${swagger.packageName:com.example.employeeoffice}")
    private String PACKAGE_NAME;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan(PACKAGE_NAME)
                .addOpenApiCustomiser(openApi -> {
                    openApi.addTagsItem(new Tag().name("address service").description("API for working with addresses"));
                    openApi.addTagsItem(new Tag().name("department service").description("API for working with departments"));
                    openApi.addTagsItem(new Tag().name("employee service").description("API for working with promo employees"));
                    openApi.addTagsItem(new Tag().name("event service").description("API for working with events"));
                    openApi.addTagsItem(new Tag().name("personal info service").description("API for working with personal info"));
                    openApi.addTagsItem(new Tag().name("role service").description("API for working with roles"));
                    openApi.addTagsItem(new Tag().name("vacancy service").description("API for working with vacancies"));
                })
                .build();
    }
}