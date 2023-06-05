package com.example.product_manager.service

import com.example.product_manager.service.dto.KoreanNetResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import kotlin.NoSuchElementException

@Component
class KoreanNetService(
    val koreanNetWebClient: WebClient,
    @Qualifier(value = "xmlMapper")
    val xmlMapper: ObjectMapper,
) {
    fun getProduct(gtin: String): KoreanNetResponse {
        val response = koreanNetWebClient
            .get()
            .uri("/mobileweb/search/barcodeSearchXml/chk/${gtin}")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        val status = xmlMapper.readTree(response).get("status")
        return if (status.asText() == "Y") {
            xmlMapper.readValue(response, KoreanNetResponse::class.java)
        } else {
            throw NoSuchElementException()
        }
    }
}