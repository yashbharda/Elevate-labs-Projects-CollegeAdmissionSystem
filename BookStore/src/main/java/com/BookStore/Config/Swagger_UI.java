package com.BookStore.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger_UI {

    @Bean
    public OpenAPI myCustomBookstoreConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("📚 Bookstore REST API")
                        .version("1.0.0")
                        .description("API for managing books and authors in a simple bookstore application.")
                        .termsOfService("https://example.com/terms")
                        .contact(new Contact()
                                .name("Yash Bharda")
                                .email("yash@example.com")
                                .url("https://github.com/YashBharda"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}
