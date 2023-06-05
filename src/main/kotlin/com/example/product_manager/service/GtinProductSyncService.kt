package com.example.product_manager.service

import com.example.product_manager.entity.GtinProductSyncState
import com.example.product_manager.entity.Product
import com.example.product_manager.repositorty.GtinProductRepository
import jakarta.transaction.Transactional
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.NoSuchElementException

@Transactional
@Service
class GtinProductSyncService(
        val koreanNetService: KoreanNetService,
        val gtinProductRepository: GtinProductRepository,
) {
    @Scheduled(fixedDelay = 500)
    fun synchronizeGtinProduct() {
        val gtinProductList = gtinProductRepository.findToBeSynchronized()
        val gtinProduct = when (gtinProductList.isEmpty()) {
            true -> null
            false -> gtinProductList[0]
        }
        if (gtinProduct !== null) {
            try {
                val koreanNetResponse = koreanNetService.getProduct(gtinProduct.gtin)
                val newProduct = Product(
                        UUID.randomUUID(),
                        koreanNetResponse.npname!!
                )
                gtinProduct.product = newProduct
                gtinProduct.synchronizedAt = LocalDateTime.now()
                println("${gtinProduct.gtin} 상품 연동이 완료되었습니다.")
            } catch(_: NoSuchElementException) {
                gtinProduct.state = GtinProductSyncState.MANUAL;
                println("${gtinProduct.gtin} 상품을 연동할 수 없습니다. 수기로 관리해주세요.")
            }
        }
    }
}