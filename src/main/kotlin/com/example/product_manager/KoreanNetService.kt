package com.example.product_manager

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriComponentsBuilder

@Component
class KoreanNetService(
   val koreanNetWebClient: WebClient
) {
    fun getProduct(gtin: String) {
        val uri = UriComponentsBuilder
            .fromUriString("http://api.koreannet.or.kr/mobileweb/search/barcodeSearchXml/chk/${gtin}")
            .queryParam("id", "aswemake01")
            .queryParam("pw", "aswemake098")
            .build()
            .toUri()

        val response = koreanNetWebClient
            .get()
            .uri(uri)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        println(response)
    }
}