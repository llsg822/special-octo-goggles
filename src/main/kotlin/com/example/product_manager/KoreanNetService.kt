package com.example.product_manager

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriComponentsBuilder

class KoreanNetService {
    private val webClient = WebClient.create()
    fun getProduct(gtin: String) {
        val uri = UriComponentsBuilder
            .fromUriString("http://api.koreannet.or.kr/mobileweb/search/barcodeSearchXml/chk/${gtin}")
            .queryParam("id", "aswemake01")
            .queryParam("pw", "aswemake098")
            .build()
            .toUri()

        val response = webClient
            .get()
            .uri(uri)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        println(response)
    }
}