package com.example.product_manager

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class KoreanNetService(
   val koreanNetWebClient: WebClient
) {
    fun getProduct(gtin: String) {
        val response = koreanNetWebClient
            .get()
            .uri("/mobileweb/search/barcodeSearchXml/chk/${gtin}")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        println(response)
    }
}