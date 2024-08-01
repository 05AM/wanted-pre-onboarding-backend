package com.wanted.server.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("원티드 백엔드 인턴십 사전미션")
                        .contact(new Contact()
                                .name("ChanMi Lee")
                                .email("anytime0224@gmail.com")));
    }
}
