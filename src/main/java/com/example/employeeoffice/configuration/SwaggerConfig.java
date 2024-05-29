package com.example.employeeoffice.configuration;

import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.GroupedOpenApi;
//@OpenAPIDefinition(
//            info = @Info(
//                    title = "Employee office",
//                    description = "There is a prototype of the BackEnd Employees Office's Core Services data. <br />" +
//                            "Data consist of addresses, authorities, departments, employees, events, personal infos, roles," +
//                            "vacancies and work schedules.",
//                    version = "1.0.0",
//                    contact = @Contact(
//                            name = "Anna Leonova",
//                            url = "https://github.com/AnnaLeonova1808"
//                    )
//            )
//    )
//@EnableSwagger2
//public class SwaggerConfig {
//
//        @Value("${swagger.packageName:com.example.employeeoffice}")
//        private String PACKAGE_NAME;
//        public static final String ADDRESS = "address service";
//        public static final String DEPARTMENT = "department service";
//        public static final String EMPLOYEE = "employee service";
//        public static final String EVENT = "event service";
//        public static final String PERSONAL_INFO = "personal info service";
//        public static final String ROLE = "role service";
//        public static final String VACANCY = "vacancy service";
//
//        @Bean
//        public Docket api() {
//            return new Docket(DocumentationType.SWAGGER_2)
//                    .select()
//                    .apis(RequestHandlerSelectors.basePackage(PACKAGE_NAME))
//                    .paths(PathSelectors.any())
//                    .build()
//                    .tags(new Tag(ADDRESS, "API for working with addresses"))
//                    .tags(new Tag(DEPARTMENT, "API for working with departments"))
//                    .tags(new Tag(EMPLOYEE, "API for working with promo employees"))
//                    .tags(new Tag(EVENT, "API for working with events"))
//                    .tags(new Tag(PERSONAL_INFO, "API for working with personal info"))
//                    .tags(new Tag(ROLE, "API for working with roles"))
//                    .tags(new Tag(VACANCY, "API for working with vacancies"));
//        }
//}
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