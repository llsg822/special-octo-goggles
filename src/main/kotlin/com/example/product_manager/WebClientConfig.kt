package com.example.product_manager

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean
    fun koreanNetWebClient(): WebClient {
        return WebClient
            .builder()
            .build()
    }
}