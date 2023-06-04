package com.example.product_manager

import org.junit.jupiter.api.Test

class KoreanNetServiceTest {
    private val koreanNetService = KoreanNetService()
    @Test
    fun getProduct() {
        koreanNetService.getProduct("8801062637560")
    }

}