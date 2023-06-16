package com.example.product_manager

import com.example.product_manager.service.KoreanNetService
import org.junit.jupiter.api.Assertions
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
        // given
        val gtin = "8801062637560" // 빼뺴로

        // when
        val koreanNetResponse = koreanNetService.getProduct(gtin)

        println(koreanNetResponse)
        // then
        Assertions.assertEquals(koreanNetResponse.status, "Y")
        Assertions.assertEquals(koreanNetResponse.gtin, gtin)
    }

    @Test
    fun getInvalidProduct() {
        // given
        val gtin = "0000000000000";

        // then
        Assertions.assertThrows(NoSuchElementException::class.java) {
            // when
            val koreanNetResponse = koreanNetService.getProduct(gtin)
            println(koreanNetResponse)
        }
    }

}