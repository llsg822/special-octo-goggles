package com.example.product_manager

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class KoreanNetService(
   val koreanNetWebClient: WebClient,
   val xmlMapper: ObjectMapper,
) {
    fun getProduct(gtin: String) {
        val response = koreanNetWebClient
            .get()
            .uri("/mobileweb/search/barcodeSearchXml/chk/${gtin}")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        val koreanNetResponse = xmlMapper.readValue(response, KoreanNetResponse::class.java)
        println(koreanNetResponse)
    }
}