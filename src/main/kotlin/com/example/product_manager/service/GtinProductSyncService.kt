package com.example.product_manager.service

import com.example.product_manager.entity.Category
import com.example.product_manager.entity.Product
import com.example.product_manager.repositorty.CategoryRepository
import com.example.product_manager.repositorty.GtinProductQueryRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Transactional
@Service
class GtinProductSyncService(
        val koreanNetService: KoreanNetService,
        val gtinProductQueryRepository: GtinProductQueryRepository,
        val categoryRepository: CategoryRepository,
) {
    @Scheduled(fixedDelay = 1000)
    fun synchronizeGtinProduct() {
        val gtinProduct = gtinProductQueryRepository.findOneToBeSynchronized() ?: return
        try {
            val koreanNetResponse = koreanNetService.getProduct(gtinProduct.gtin)
            val category = categoryRepository.findByIdOrNull(koreanNetResponse.kanclasscode!!) ?: run {
                val newCategory = Category(
                        id = koreanNetResponse.kanclasscode,
                        name = null
                )
                newCategory
            }
            val newProduct = Product(
                    name = koreanNetResponse.npname!!,
                    imageUrl = koreanNetResponse.imgpath1!!,
                    category = category
            )
            gtinProduct.successToSynchronize(product = newProduct)
            println("${gtinProduct.gtin} 상품 연동이 완료되었습니다.")
        } catch(_: NoSuchElementException) {
            gtinProduct.failToSynchronize();
            println("${gtinProduct.gtin} 상품을 연동할 수 없습니다. 수기로 관리해주세요.")
        }
    }
}