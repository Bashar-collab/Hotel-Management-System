package com.custempmanag.hotelroombooking.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Room Booking API")
                        .version("1.0")
                        .description("API documentation for room and booking management system")
                        .contact(new Contact().name("Your Name").email("your.email@example.com")));
    }
}