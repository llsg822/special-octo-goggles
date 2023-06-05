package com.example.product_manager.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder

@Configuration
class WebClientConfig {
    @Bean
    fun koreanNetWebClient(): WebClient {
        return WebClient
            .builder()
            .baseUrl("http://api.koreannet.or.kr")
            .filter { request, next ->
                val uri = UriComponentsBuilder
                    .fromUri(request.url())
                    .queryParam("id", "aswemake01")
                    .queryParam("pw", "aswemake098")
                    .build()
                    .toUri()
                val filtered = ClientRequest.from(request)
                    .url(uri)
                    .build()
                next.exchange(filtered)
            }
            .build()
    }
}