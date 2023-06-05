package com.example.product_manager

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KoreanNetServiceTest(
    @Autowired
    val koreanNetService: KoreanNetService
) {
    @Test
    fun getProduct() {
        koreanNetService.getProduct("8801062637560")
    }

}