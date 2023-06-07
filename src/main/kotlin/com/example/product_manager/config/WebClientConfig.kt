package com.example.product_manager.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder

@Configuration
class WebClientConfig(
        @Value("\${koreanNet.id}")
        private val koreanNetId: String,
        @Value("\${koreanNet.pw}")
        private val koreanNetPw: String,
) {
    @Bean
    fun koreanNetWebClient(): WebClient {
        return WebClient
                .builder()
                .baseUrl("http://api.koreannet.or.kr")
                .filter { request, next ->
                    val uri = UriComponentsBuilder
                            .fromUri(request.url())
                            .queryParam("id", koreanNetId)
                            .queryParam("pw", koreanNetPw)
                            .build()
                            .toUri()
                    val filtered = ClientRequest.from(request)
                            .url(uri)
                            .build()
                    println(filtered.url())
                    next.exchange(filtered)
                }
                .build()
    }
}