package com.example.product_manager.service

import com.example.product_manager.repositorty.GtinProductRepository
import com.example.product_manager.service.dto.GtinProductResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class GtinProductQueryService(
        val gtinProductRepository: GtinProductRepository,
) {
    fun getProduct(): List<GtinProductResponse> {
        return gtinProductRepository.findAllWithProduct().map { gtinProduct ->
            GtinProductResponse(
                    gtin = gtinProduct.gtin,
                    name = gtinProduct.product?.name,
                    imageUrl = gtinProduct.product?.imageUrl,
                    categoryId = gtinProduct.product?.category?.id,
                    categoryName = gtinProduct.product?.category?.name,
                    syncState = gtinProduct.state,
                    synchronizedAt = gtinProduct.synchronizedAt,
            )
        }
    }
}